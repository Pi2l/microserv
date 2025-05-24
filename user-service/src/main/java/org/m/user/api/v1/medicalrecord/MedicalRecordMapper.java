package org.m.user.api.v1.medicalrecord;

import org.m.user.model.Doctor;
import org.m.user.model.MedicalRecord;
import org.m.user.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface MedicalRecordMapper {

  MedicalRecordMapper INSTANCE = Mappers.getMapper( MedicalRecordMapper.class );

  @Mapping(target = "patientId", expression = "java( entity.getPatient().getId() )")
  @Mapping(target = "doctorId", expression = "java( entity.getDoctor().getId() )")
  MedicalRecordDto entityToDto(MedicalRecord entity);

  @Mapping(target = "patient", expression = "java( dto.getPatientId() != null ? toPatientEntity( dto.getPatientId() ) : null )")
  @Mapping(target = "doctor", expression = "java( dto.getDoctorId() != null ? toDoctorEntity( dto.getDoctorId() ) : null )")
  MedicalRecord dtoToEntity(MedicalRecordDto dto);

  default Patient toPatientEntity(Long id) {
    return Patient.builder().id( id ).build();
  }

  default Doctor toDoctorEntity(Long id) {
    return Doctor.builder().id( id ).build();
  }

}