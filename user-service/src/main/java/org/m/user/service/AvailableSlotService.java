package org.m.user.service;

import lombok.AllArgsConstructor;

import org.m.lib.repository.PrimaryRepository;
import org.m.lib.service.CrudService;
import org.m.user.model.AvailableSlot;
import org.m.user.repository.AvailableSlotRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AvailableSlotService extends CrudService<AvailableSlot, Long> {

  private final AvailableSlotRepository repository;

  @Override
  protected PrimaryRepository<AvailableSlot, Long> getRepository() {
    return repository;
  }
}
