package org.m.clinic.api.v1.patient;

import org.m.clinic.api.v1.user.UserDto;
import org.m.clinic.api.v1.user.UserMapper;
import org.m.clinic.model.Patient;
import org.m.clinic.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface PatientMapper {

  PatientMapper INSTANCE = Mappers.getMapper( PatientMapper.class );

  @Mapping(target = "userDto", expression = "java( toUserDto( entity.getUser() ) )")
  PatientDto entityToDto(Patient entity);

  @Mapping(target = "user", expression = "java( dto.getUserDto() != null ? toUserEntity( dto.getUserDto() ) : null)")
  Patient dtoToEntity(PatientDto dto);

  default UserDto toUserDto(User entity) {
    return UserMapper.INSTANCE.entityToDto( entity );
  }

  default User toUserEntity(UserDto dto) {
    return UserMapper.INSTANCE.dtoToEntity( dto );
  }
}