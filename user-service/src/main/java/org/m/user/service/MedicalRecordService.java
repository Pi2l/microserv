package org.m.user.service;

import lombok.AllArgsConstructor;

import org.m.lib.repository.PrimaryRepository;
import org.m.lib.service.CrudService;
import org.m.user.model.MedicalRecord;
import org.m.user.repository.MedicalRecordRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MedicalRecordService extends CrudService<MedicalRecord, Long> {

  private final MedicalRecordRepository repository;

  @Override
  protected PrimaryRepository<MedicalRecord, Long> getRepository() {
    return repository;
  }
}
