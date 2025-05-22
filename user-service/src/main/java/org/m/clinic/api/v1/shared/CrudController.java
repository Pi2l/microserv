package org.m.clinic.api.v1.shared;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Range;
import org.m.lib.model.HasIdentifier;
import org.m.lib.service.CrudService;
import org.m.clinic.repository.rsql.CustomRsqlVisitor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
public abstract class CrudController<Entity extends HasIdentifier, Dto extends AbstractDto<Entity>> {

  protected abstract CrudService<Entity, Long> getService();

  protected abstract Dto convertToDto(Entity entity);
  protected abstract Entity mapDtoToEntity(Dto requestBody, Entity entityToFill);

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public PageResponse<Dto> getAll(
      @RequestParam(defaultValue = "0", required = false) @PositiveOrZero Integer index,
      @RequestParam(defaultValue = "30", required = false) @Range(max = 100) Integer size,
      @RequestParam(required = false) Sort sort,
      @RequestParam(required = false) String search) {
    sort = mapSortProperties(sort);

    Specification<Entity> specification = null;
    if (search != null) {
      specification = getSpecificationFromSearch(search);
    }

    Pageable pageable = PageRequest.of(index, size, sort);
    Page<Entity> entityPage = getService().getAll(pageable, specification);
    List<Dto> responses = entityPage.getContent().stream()
        .map(this::convertToDto).toList();

    return new PageResponse<>(responses, entityPage.getTotalElements(), entityPage.getNumber(), entityPage.getSize());
  }

  @ResponseBody
  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Dto get(@PathVariable Long id) {
    Entity entity = getEntity(id);
    return convertToDto(entity);
  }

  @ResponseBody
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public Dto create(@Valid @RequestBody Dto requestBody) {
    Entity entity = mapDtoToEntity(requestBody, requestBody.getNewEntity());
    beforeEntityCreation(entity);
    Entity createdEntity = createEntity(entity, requestBody);
    return convertToDto(createdEntity);
  }

  protected void beforeEntityCreation(Entity entity) {
  }

  @ResponseBody
  @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Dto update(@PathVariable Long id, @Valid @RequestBody Dto requestBody) {
    Entity entity = getEntity(id);
    Entity entityToUpdate = mapDtoToEntity(requestBody, entity);

    beforeEntityUpdate(entityToUpdate);
    Entity updatedEntity = updateEntity(entityToUpdate, requestBody);
    return convertToDto(updatedEntity);
  }

  protected void beforeEntityUpdate(Entity entityToUpdate) {
  }

  @ResponseBody
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    getEntity(id);
    deleteEntity(id);
  }

  protected Entity getEntity(Long id) {
    return getEntity(id, getService(), true);
  }

  protected <T extends HasIdentifier> T getEntity(Long id, CrudService<T, Long> service, boolean throwException) {
    T entity = service.get(id);
    if (entity == null && throwException) {
      throw new ItemNotFoundException(id);
    }
    return entity;
  }

  protected Entity createEntity(Entity entity, Dto dto) {
    return getService().create(entity);
  }

  protected Entity updateEntity(Entity entity, Dto dto) {
    return getService().update(entity);
  }

  protected void deleteEntity(Long id) {
    getService().delete(id);
  }

  protected Specification<Entity> getSpecificationFromSearch(String search) {
    Node rootNode = new RSQLParser().parse(search);
    return rootNode.accept(new CustomRsqlVisitor<>());
  }

  protected Sort mapSortProperties(Sort sort) {
    if (sort == null) {
      return Sort.unsorted();
    }

    List<Sort.Order> sorts = sort.stream()
        .map(order -> new Sort.Order(order.getDirection(), order.getProperty(), order.getNullHandling()))
        .toList();
    return Sort.by(sorts);
  }
}
