package org.m.clinic.service;

import lombok.AllArgsConstructor;
import org.m.clinic.model.Patient;
import org.m.clinic.repository.PatientRepository;
import org.m.clinic.repository.PrimaryRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PatientService extends CrudService<Patient, Long> {

  private final PatientRepository repository;

  @Override
  protected PrimaryRepository<Patient, Long> getRepository() {
    return repository;
  }
}
