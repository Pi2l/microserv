package org.m.clinic.api.v1.availableslot;

import lombok.RequiredArgsConstructor;
import org.m.clinic.api.v1.shared.CrudController;
import org.m.clinic.model.AvailableSlot;
import org.m.clinic.model.Doctor;
import org.m.clinic.service.AvailableSlotService;
import org.m.clinic.service.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(AvailableSlotController.API_URL)
public class AvailableSlotController extends CrudController<AvailableSlot, AvailableSlotDto> {

  public static final String API_URL = "/api/v1/available-slots";

  private final AvailableSlotService availableSlotService;

  @Override
  protected CrudService<AvailableSlot, Long> getService() {
    return availableSlotService;
  }

  @Override
  protected AvailableSlotDto convertToDto(AvailableSlot entity) {
    var dto = new AvailableSlotDto();
    dto.setId(entity.getId());
    dto.setDoctorId(entity.getDoctor().getId());
    dto.setSlotTimeStart(entity.getSlotTimeStart());
    dto.setDuration(entity.getDuration());
    dto.setIsBooked(entity.getIsBooked());
    return dto;
  }

  @Override
  protected AvailableSlot mapDtoToEntity(AvailableSlotDto requestBody, AvailableSlot entityToFill) {
    entityToFill.setId(requestBody.getId());
    entityToFill.setDoctor(Doctor.builder().id(requestBody.getDoctorId()).build());
    entityToFill.setSlotTimeStart(requestBody.getSlotTimeStart());
    entityToFill.setDuration(requestBody.getDuration());
    return entityToFill;
  }
}
