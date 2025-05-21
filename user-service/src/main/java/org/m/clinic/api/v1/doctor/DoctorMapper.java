package org.m.clinic.api.v1.doctor;

import org.m.clinic.api.v1.patient.PatientDto;
import org.m.clinic.api.v1.user.UserDto;
import org.m.clinic.api.v1.user.UserMapper;
import org.m.clinic.model.Doctor;
import org.m.clinic.model.Patient;
import org.m.clinic.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface DoctorMapper {

  DoctorMapper INSTANCE = Mappers.getMapper( DoctorMapper.class );

  @Mapping(target = "userDto", expression = "java( toUserDto( entity.getUser() ) )")
  DoctorDto entityToDto(Doctor entity);

  @Mapping(target = "user", expression = "java( dto.getUserDto() != null ? toUserEntity( dto.getUserDto() ) : null)")
  Doctor dtoToEntity(DoctorDto dto);

  default UserDto toUserDto(User entity) {
    return UserMapper.INSTANCE.entityToDto( entity );
  }

  default User toUserEntity(UserDto dto) {
    return UserMapper.INSTANCE.dtoToEntity( dto );
  }
}