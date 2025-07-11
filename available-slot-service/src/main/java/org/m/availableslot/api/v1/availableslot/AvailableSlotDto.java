package org.m.availableslot.api.v1.availableslot;

import java.time.ZonedDateTime;

import org.m.availableslot.api.v1.shared.AbstractDto;
import org.m.availableslot.model.AvailableSlot;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

  @JsonIgnore
  @Override
  public AvailableSlot getNewEntity() {
    return new AvailableSlot();
  }
}
