package org.m.clinic.service;

import lombok.AllArgsConstructor;
import org.m.clinic.model.MedicalRecord;
import org.m.clinic.repository.MedicalRecordRepository;
import org.m.clinic.repository.PrimaryRepository;
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
