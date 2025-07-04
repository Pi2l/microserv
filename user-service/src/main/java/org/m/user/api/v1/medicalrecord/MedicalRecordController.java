package org.m.user.api.v1.medicalrecord;

import lombok.RequiredArgsConstructor;

import org.m.lib.service.CrudService;
import org.m.user.api.v1.shared.CrudController;
import org.m.user.model.Doctor;
import org.m.user.model.MedicalRecord;
import org.m.user.model.Patient;
import org.m.user.service.MedicalRecordService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping(MedicalRecordController.API_URL)
public class MedicalRecordController extends CrudController<MedicalRecord, MedicalRecordDto> {

  public static final String API_URL = "/api/v1/medical-records";

  private final MedicalRecordService service;

  @Override
  protected CrudService<MedicalRecord, Long> getService() {
    return service;
  }

  @Override
  protected MedicalRecordDto convertToDto(MedicalRecord medicalRecord) {
    var dto = new MedicalRecordDto();
    dto.setId(medicalRecord.getId());
    dto.setPatientId(medicalRecord.getPatient() == null ? null : medicalRecord.getPatient().getId());
    dto.setDoctorId(medicalRecord.getDoctor() == null ? null : medicalRecord.getDoctor().getId());
    dto.setDiagnosis(medicalRecord.getDiagnosis());
    dto.setPrescription(medicalRecord.getPrescription());
    return dto;
  }

  @Override
  protected MedicalRecord mapDtoToEntity(MedicalRecordDto dto, MedicalRecord entityToFill) {
    entityToFill.setId(dto.getId());
    entityToFill.setPatient(Patient.builder().id(dto.getId()).build());
    entityToFill.setDoctor(Doctor.builder().id(dto.getDoctorId()).build());
    entityToFill.setDiagnosis(dto.getDiagnosis());
    entityToFill.setPrescription(dto.getPrescription());
    return entityToFill;
  }

}
