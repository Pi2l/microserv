package org.m.clinic.service;

import lombok.AllArgsConstructor;
import org.m.clinic.model.AvailableSlot;
import org.m.clinic.repository.AvailableSlotRepository;
import org.m.clinic.repository.PrimaryRepository;
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
