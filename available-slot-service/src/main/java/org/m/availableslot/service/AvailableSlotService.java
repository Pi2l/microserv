package org.m.availableslot.service;

import org.m.availableslot.model.AvailableSlot;
import org.m.availableslot.repository.AvailableSlotRepository;
import org.m.lib.repository.PrimaryRepository;
import org.m.lib.service.CrudService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AvailableSlotService extends CrudService<AvailableSlot, Long> {

  private final AvailableSlotRepository repository;

  @Override
  protected PrimaryRepository<AvailableSlot, Long> getRepository() {
    return repository;
  }
}
