package org.m.clinic.service;

import lombok.AllArgsConstructor;
import org.m.clinic.model.Patient;
import org.m.clinic.repository.PatientRepository;
import org.m.clinic.repository.PrimaryRepository;
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
}
