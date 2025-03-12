package org.m.clinic.controller.shared;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.m.clinic.model.HasIdentifier;
import org.m.clinic.repository.rsql.CustomRsqlVisitor;
import org.m.clinic.service.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@AllArgsConstructor
public abstract class CrudController<
        Entity extends HasIdentifier,
        Service extends CrudService<Entity, Long>,
        Dto extends AbstractDto<Entity>
> {

  protected final Service service;

  protected abstract Dto convertToDto(Entity entity);
  protected abstract Entity mapDtoToEntity(Dto requestBody, Entity entityToFill);

  @GetMapping
  @ResponseBody
  public PageResponse<Dto> getAll(
          @RequestParam(defaultValue = "0", required = false) @PositiveOrZero Integer index,
          @RequestParam(defaultValue = "30", required = false) @Range(max = 100) Integer size,
          @PathParam(value = "sort") Sort sort,
          @RequestParam(required = false) String search
  ) {
    sort = mapSortProperties( sort );

    Specification<Entity> specification = null;
    if (search != null) {
      specification = getSpecificationFromSearch(search);
    }

    Pageable pageable = PageRequest.of(index, size, sort);
    Page<Entity> entityPage = service.getAll( pageable, specification );
    List<Dto> responses = entityPage.getContent().stream()
            .map( this::convertToDto ).toList();

    return new PageResponse<>(responses, entityPage.getTotalElements(), entityPage.getNumber(), entityPage.getSize());
  }

  @ResponseBody
  @GetMapping("/{id}")
  public Dto get(@PathVariable Long id) {
    Entity entity = getEntity(id);
    return convertToDto( entity );
  }

  @ResponseBody
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Dto create(@Validated @RequestBody Dto requestBody) {
    Entity entity = mapDtoToEntity( requestBody, requestBody.getNewEntity() );
    Entity createdEntity = createEntity( entity, requestBody );
    return convertToDto( createdEntity );
  }

  @ResponseBody
  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Dto update(@PathVariable Long id, @Validated @RequestBody Dto requestBody) {
    Entity entity = getEntity(id);
    Entity entityToUpdate = mapDtoToEntity( requestBody, entity );
    Entity updatedEntity = updateEntity( entityToUpdate, requestBody );
    return convertToDto( updatedEntity );
  }

  @ResponseBody
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    getEntity(id);
    deleteEntity(id);
  }

  protected Entity getEntity(Long id) {
    Entity entity = service.get(id);
    if (entity == null) {
      throw new ItemNotFoundException(id);
    }
    return entity;
  }

  protected Entity createEntity(Entity entity, Dto dto) {
    return service.create( entity );
  }

  protected Entity updateEntity(Entity entity, Dto dto) {
    return service.update( entity );
  }

  protected void deleteEntity(Long id) {
    service.delete(id);
  }

  protected Specification<Entity> getSpecificationFromSearch(String search) {
    Node rootNode = new RSQLParser().parse(search);
    return rootNode.accept(new CustomRsqlVisitor<>());
  }

  protected Sort mapSortProperties(Sort sort ) {
    if ( sort == null ) {
      return Sort.unsorted();
    }

    List<Sort.Order> sorts = sort.stream()
        .map(order -> new Sort.Order(order.getDirection(), order.getProperty(), order.getNullHandling()))
        .toList();
    return Sort.by( sorts );
  }
}
