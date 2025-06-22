package org.m.availableslot.api.v1.shared;

public class ItemNotFoundException extends RuntimeException {
  public ItemNotFoundException(Long id) {
    super("Item with id " + id + " not found");
  }
}
