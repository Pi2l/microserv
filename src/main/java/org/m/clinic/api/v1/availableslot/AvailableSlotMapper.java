package org.m.clinic.api.v1.availableslot;

import org.m.clinic.model.AvailableSlot;
import org.m.clinic.model.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface AvailableSlotMapper {

  AvailableSlotMapper INSTANCE = Mappers.getMapper( AvailableSlotMapper.class );

  @Mapping(target = "doctorId", expression = "java( entity.getDoctor().getId() )")
  AvailableSlotDto entityToDto(AvailableSlot entity);

  @Mapping(target = "doctor", expression = "java( dto.getDoctorId() != null ? toDoctorEntity( dto.getDoctorId() ) : null)")
  AvailableSlot dtoToEntity(AvailableSlotDto dto);

  default Doctor toDoctorEntity(Long id) {
    return Doctor.builder().id( id ).build();
  }
}