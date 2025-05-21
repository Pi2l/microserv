package org.m.clinic.api.v1.appointment;

import lombok.RequiredArgsConstructor;
import org.m.clinic.api.v1.shared.CrudController;
import org.m.clinic.model.Appointment;
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
    return AppointmentMapper.INSTANCE.entityToDto( appointment );
  }

  @Override
  protected Appointment mapDtoToEntity(AppointmentDto requestBody, Appointment entityToFill) {
    return AppointmentMapper.INSTANCE.dtoToEntity( requestBody );
  }
}
