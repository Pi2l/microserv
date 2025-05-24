package org.m.clinic.api.v1.medicalrecord;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.m.clinic.api.v1.shared.AbstractDto;
import org.m.clinic.model.MedicalRecord;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MedicalRecordDto extends AbstractDto<MedicalRecord> {

  private Long id;
  private Long patientId;
  private Long doctorId;

  @NotNull
  private String diagnosis;

  @NotNull
  private String prescription;

  @Override
  public MedicalRecord getNewEntity() {
    return new MedicalRecord();
  }
}