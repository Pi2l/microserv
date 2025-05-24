package org.m.user.service;

import lombok.AllArgsConstructor;

import org.m.lib.repository.PrimaryRepository;
import org.m.user.model.Patient;
import org.m.user.model.Role;
import org.m.user.repository.PatientRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PatientService extends AbstractUserService<Patient> {

  private final PatientRepository repository;
  private final UserService userService;

  @Override
  protected PrimaryRepository<Patient, Long> getRepository() {
    return repository;
  }

  @Override
  public Patient create(Patient entity) {
    checkUserNotConnectedToOtherEntity( entity );
    return super.create( entity );
  }

  @Override
  protected UserService getUserService() {
    return userService;
  }

  @Override
  public Specification<Patient> getDefaultFilter() {
    Specification<Patient> filter = getRoleFilter(Role.PATIENT.name());
    return filter.and(super.getDefaultFilter());
  }
}
