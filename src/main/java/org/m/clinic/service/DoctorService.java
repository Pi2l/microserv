package org.m.clinic.service;

import lombok.AllArgsConstructor;
import org.m.clinic.model.Doctor;
import org.m.clinic.repository.DoctorRepository;
import org.m.clinic.repository.PrimaryRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DoctorService extends CrudService<Doctor, Long> {

  private final DoctorRepository doctorRepository;

  @Override
  protected PrimaryRepository<Doctor, Long> getRepository() {
    return doctorRepository;
  }
}
