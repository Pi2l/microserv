package org.m.user.service;

import lombok.AllArgsConstructor;

import org.m.lib.repository.PrimaryRepository;
import org.m.user.model.Doctor;
import org.m.user.model.Role;
import org.m.user.repository.DoctorRepository;
import org.springframework.data.jpa.domain.Specification;
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

  @Override
  public Specification<Doctor> getDefaultFilter() {
    Specification<Doctor> filter = getRoleFilter(Role.DOCTOR.name());
    return filter.and(super.getDefaultFilter());
  }
}
