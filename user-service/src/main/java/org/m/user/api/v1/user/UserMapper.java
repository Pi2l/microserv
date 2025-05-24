package org.m.user.api.v1.user;

import org.m.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

  UserDto entityToDto(User entity);

  User dtoToEntity(UserDto dto);
}