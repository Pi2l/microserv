package org.m.user.service;

import org.m.lib.model.HasIdentifier;
import org.m.lib.service.CrudService;
import org.m.user.model.HasUser;
import org.springframework.data.jpa.domain.Specification;

public abstract class AbstractUserService<T extends HasUser & HasIdentifier>
        extends CrudService<T, Long> {

  protected abstract UserService getUserService();

  protected void checkUserNotConnectedToOtherEntity(T entity) {
    var user = entity.getUser();
    if (user != null) {
      var existingUser = getUserService().findByEmail( user.getEmail() );
      if ( existingUser == null ) {
        entity.setUser( getUserService().create( user ) );
        return;
      }
      var existingPatient = this.get( existingUser.getId() );
      if (existingPatient != null) {
        throw new IllegalArgumentException("User is already connected to another entity");
      }
      entity.setUser( existingUser );
    }
  }

  protected Specification<T> getRoleFilter(String role) {
    return (root, cq, cb) -> cb.equal(root.get("user").get("role"), role);
  }
}
