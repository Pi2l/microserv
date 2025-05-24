package org.m.appointment.api.v1.appointment;

import org.m.appointment.model.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface AppointmentMapper {

  AppointmentMapper INSTANCE = Mappers.getMapper( AppointmentMapper.class );

  AppointmentDto entityToDto(Appointment entity);

  Appointment dtoToEntity(AppointmentDto dto);

}