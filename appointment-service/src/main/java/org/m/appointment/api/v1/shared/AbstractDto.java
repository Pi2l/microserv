package org.m.appointment.api.v1.shared;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

public abstract class AbstractDto<Entity> {

  @JsonIgnore
  @Schema(hidden = true)
  public abstract Entity getNewEntity();
}