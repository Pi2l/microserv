package org.m.lib.api.shared;

public class ItemNotFoundException extends RuntimeException {
  public ItemNotFoundException(Long id) {
    super("Item with id " + id + " not found");
  }
}
