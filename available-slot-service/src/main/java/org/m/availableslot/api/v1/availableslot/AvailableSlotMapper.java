package org.m.availableslot.api.v1.availableslot;

import org.m.availableslot.model.AvailableSlot;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface AvailableSlotMapper {

  AvailableSlotMapper INSTANCE = Mappers.getMapper( AvailableSlotMapper.class );

  AvailableSlotDto entityToDto(AvailableSlot entity);

  AvailableSlot dtoToEntity(AvailableSlotDto dto);

}