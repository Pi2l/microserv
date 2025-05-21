package org.m.clinic.api.v1.availableslot;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.m.clinic.api.v1.shared.AbstractDto;
import org.m.clinic.model.AvailableSlot;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AvailableSlotDto extends AbstractDto<AvailableSlot> {

  private Long id;

  @NotNull
  private Long doctorId;

  @NotNull
  private ZonedDateTime slotTimeStart;

  @NotNull
  private Long duration;

  private Boolean isBooked = false;

  @Override
  public AvailableSlot getNewEntity() {
    return new AvailableSlot();
  }
}
