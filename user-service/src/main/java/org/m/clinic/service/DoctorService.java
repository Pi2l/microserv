package org.m.clinic.service;

import lombok.AllArgsConstructor;
import org.m.clinic.model.Doctor;
import org.m.clinic.repository.DoctorRepository;
import org.m.lib.repository.PrimaryRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DoctorService extends AbstractUserService<Doctor> {

  private final DoctorRepository doctorRepository;
  private final UserService userService;

  @Override
  protected PrimaryRepository<Doctor, Long> getRepository() {
    return doctorRepository;
  }

  @Override
  public Doctor create(Doctor entity) {
    checkUserNotConnectedToOtherEntity( entity );
    return super.create( entity );
  }

  @Override
  protected UserService getUserService() {
    return userService;
  }
}
