package org.m.clinic.controller.appointment;

import org.m.clinic.controller.shared.CrudController;
import org.m.clinic.model.Appointment;
import org.m.clinic.model.Doctor;
import org.m.clinic.model.Patient;
import org.m.clinic.service.AppointmentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppointmentController.APPOINTMENT_API)
public class AppointmentController extends CrudController<Appointment, AppointmentService, AppointmentDto> {

  public static final String APPOINTMENT_API = "/api/v1/appointments";

  public AppointmentController(AppointmentService appointmentService) {
    super(appointmentService);
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
