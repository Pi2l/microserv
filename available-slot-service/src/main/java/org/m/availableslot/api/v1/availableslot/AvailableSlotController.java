package org.m.availableslot.api.v1.availableslot;

import org.m.availableslot.api.v1.shared.CrudController;
import org.m.availableslot.model.AvailableSlot;
import org.m.availableslot.service.AvailableSlotService;
import org.m.lib.service.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

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
    return AvailableSlotMapper.INSTANCE.entityToDto(entity);
  }

  @Override
  protected AvailableSlot mapDtoToEntity(AvailableSlotDto requestBody, AvailableSlot entityToFill) {
    requestBody.setId(entityToFill.getId());
    return AvailableSlotMapper.INSTANCE.dtoToEntity(requestBody);
  }
}
