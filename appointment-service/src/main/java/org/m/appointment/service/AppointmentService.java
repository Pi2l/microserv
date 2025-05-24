package org.m.appointment.service;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.m.appointment.api.v1.appointment.AppointmentDto;
import org.m.appointment.api.v1.shared.ItemNotFoundException;
import org.m.appointment.api.v1.user.DoctorDto;
import org.m.appointment.api.v1.user.PatientDto;
import org.m.appointment.model.Appointment;
import org.m.appointment.repository.AppointmentRepository;
import org.m.lib.repository.PrimaryRepository;
import org.m.lib.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppointmentService extends CrudService<Appointment, Long> {

  private final AppointmentRepository appointmentRepository;
  private final RestTemplate restTemplate;

  @Override
  protected PrimaryRepository<Appointment, Long> getRepository() {
    return this.appointmentRepository;
  }

  @Override
  public Appointment create(Appointment appointment) {
    checkPresence(appointment.getPatientId(), "http://USER/api/v1/patients/", PatientDto.class);
    checkPresence(appointment.getDoctorId(), "http://USER/api/v1/doctors/", DoctorDto.class);

    return super.create(appointment);
  }

  @Override
  public Appointment update(Appointment appointment) {
    checkPresence(appointment.getPatientId(), "http://USER/api/v1/patients/", PatientDto.class);
    checkPresence(appointment.getDoctorId(), "http://USER/api/v1/doctors/", DoctorDto.class);

    return super.update(appointment);
  }

  private <T> void checkPresence(Long id, String url, Class<T> klass) {
    T entity = null;
    try {
      String fullUrl = restTemplate.getUriTemplateHandler().expand(url + id).toString();
      System.out.println("Making GET request to: " + fullUrl + " for class: " + klass.getSimpleName());
      entity = restTemplate.getForObject(fullUrl, klass);
    } catch (HttpClientErrorException.NotFound ex) {
      // do nothing
    } catch (HttpClientErrorException e) {
      throw new IllegalArgumentException("Error while checking presence of entity with id " + id, e);
    }
    if (entity == null) {
      System.err.println("Entity with id " + id + " not found. Class: " + klass.getSimpleName());
      throw new ItemNotFoundException(id);
    }
  }
}
