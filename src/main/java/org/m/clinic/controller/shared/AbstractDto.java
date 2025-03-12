package org.m.clinic.controller.shared;

public abstract class AbstractDto<Entity> {
  public abstract Entity getNewEntity();
}