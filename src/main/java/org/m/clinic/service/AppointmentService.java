package org.m.clinic.service;

import org.m.clinic.model.Appointment;
import org.m.clinic.repository.AppointmentRepository;
import org.m.clinic.repository.PrimaryRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppointmentService extends CrudService<Appointment, Long> {

  private final AppointmentRepository appointmentRepository;

  @Override
  protected PrimaryRepository<Appointment, Long> getRepository() {
    return this.appointmentRepository;
  }

}
