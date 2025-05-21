package org.m.clinic.api.v1.appointment;

import org.m.clinic.model.Appointment;
import org.m.clinic.model.Doctor;
import org.m.clinic.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface AppointmentMapper {

  AppointmentMapper INSTANCE = Mappers.getMapper( AppointmentMapper.class );

  @Mapping(target = "patientId", expression = "java( entity.getPatient().getId() )")
  @Mapping(target = "doctorId", expression = "java( entity.getDoctor().getId() )")
  AppointmentDto entityToDto(Appointment entity);

  @Mapping(target = "patient", expression = "java( dto.getPatientId() != null ? toPatientEntity( dto.getPatientId() ) : null )")
  @Mapping(target = "doctor", expression = "java( dto.getDoctorId() != null ? toDoctorEntity( dto.getDoctorId() ) : null )")
  Appointment dtoToEntity(AppointmentDto dto);

  default Patient toPatientEntity(Long id) {
    return Patient.builder().id( id ).build();
  }

  default Doctor toDoctorEntity(Long id) {
    return Doctor.builder().id( id ).build();
  }
}