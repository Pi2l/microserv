package org.m.lib.service;

import java.util.List;

import org.m.lib.model.HasIdentifier;
import org.m.lib.repository.PrimaryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public abstract class CrudService<T extends HasIdentifier, IdType> {

  protected abstract PrimaryRepository<T, IdType> getRepository();

  public List<T> getAll() {
    return getRepository().findAll();
  }

  public Page<T> getAll(Pageable pageable, Specification<T> filter) {
    return getRepository().findAll(filter, pageable);
  }

  public T get(IdType id) {
    return getRepository().findOne(buildEqualSpec("id", id))
        .orElse(null);
  }

  public T get(Specification<T> filter) {
    return getRepository().findOne(filter)
        .orElse(null);
  }

  public T create(T entity) {
    return getRepository().save(entity);
  }

  public T update(T entity) {
    return getRepository().save(entity);
  }

  public void delete(IdType id) {
    getRepository().deleteById(id);
  }

  protected Specification<T> buildEqualSpec(String fieldName, Object value) {
    return (root, cq, cb) -> cb.equal(root.get(fieldName), value);
  }
}
