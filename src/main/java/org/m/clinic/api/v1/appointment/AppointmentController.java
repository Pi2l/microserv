package org.m.clinic.api.v1.appointment;

import lombok.RequiredArgsConstructor;
import org.m.clinic.api.v1.shared.CrudController;
import org.m.clinic.model.Appointment;
import org.m.clinic.model.Doctor;
import org.m.clinic.model.Patient;
import org.m.clinic.service.AppointmentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(AppointmentController.API_URL)
public class AppointmentController extends CrudController<Appointment, AppointmentDto> {

  public static final String API_URL = "/api/v1/appointments";

  private final AppointmentService appointmentService;

  @Override
  protected AppointmentService getService() {
    return appointmentService;
  }

  @Override
  protected AppointmentDto convertToDto(Appointment appointment) {
    var dto = new AppointmentDto();
    dto.setId(appointment.getId());
    dto.setPatientId(appointment.getPatient().getId());
    dto.setDoctorId(appointment.getDoctor().getId());
    dto.setStatus(appointment.getStatus());
    dto.setAppointmentTime(appointment.getAppointmentTime());
    return dto;
  }

  @Override
  protected Appointment mapDtoToEntity(AppointmentDto requestBody, Appointment entityToFill) {
    var patient = Patient.builder().id(requestBody.getPatientId()).build();
    var doctor = Doctor.builder().id(requestBody.getDoctorId()).build();
    doctor.setId(requestBody.getDoctorId());
    patient.setId(requestBody.getPatientId());

    entityToFill.setPatient(patient);
    entityToFill.setDoctor(doctor);
    entityToFill.setStatus(requestBody.getStatus());
    entityToFill.setAppointmentTime(requestBody.getAppointmentTime());
    return entityToFill;
  }
}
