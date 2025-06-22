package org.m.appointment.service;

import org.m.appointment.api.v1.shared.ItemNotFoundException;
import org.m.appointment.api.v1.user.DoctorDto;
import org.m.appointment.api.v1.user.PatientDto;
import org.m.appointment.model.Appointment;
import org.m.appointment.repository.AppointmentRepository;
import org.m.lib.repository.PrimaryRepository;
import org.m.lib.service.CrudService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppointmentService extends CrudService<Appointment, Long> {

  private final AppointmentRepository appointmentRepository;
  private final RestTemplate restTemplate;
  private final KafkaTemplate<String, String> kafkaTemplate;
  private final ObjectMapper objectMapper;

  @Override
  protected PrimaryRepository<Appointment, Long> getRepository() {
    return this.appointmentRepository;
  }

  @Override
  public Appointment create(Appointment appointment) {
    checkPresence(appointment.getPatientId(), "http://USER/api/v1/patients/", PatientDto.class);
    checkPresence(appointment.getDoctorId(), "http://USER/api/v1/doctors/", DoctorDto.class);

    Appointment createdAppointment = super.create(appointment);
    sendAppointmentToKafka(createdAppointment);
    return createdAppointment;
  }

  @Override
  public Appointment update(Appointment appointment) {
    checkPresence(appointment.getPatientId(), "http://USER/api/v1/patients/", PatientDto.class);
    checkPresence(appointment.getDoctorId(), "http://USER/api/v1/doctors/", DoctorDto.class);

    Appointment updatedAppointment = super.update(appointment);
    sendAppointmentToKafka(updatedAppointment);
    return updatedAppointment;
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

  private void sendAppointmentToKafka(Appointment appointment) {
    try {
      String appointmentJson = objectMapper.writeValueAsString(appointment);
      kafkaTemplate.send("appointments", appointmentJson);
      System.out.println("Sent appointment to Kafka: " + appointmentJson);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Error serializing appointment to JSON", e);
    }
  }
}
