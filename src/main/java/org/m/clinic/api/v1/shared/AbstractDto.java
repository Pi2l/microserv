package org.m.clinic.api.v1.shared;

public abstract class AbstractDto<Entity> {
  public abstract Entity getNewEntity();
}