package org.m.clinic.api.v1.user;

import lombok.RequiredArgsConstructor;
import org.m.clinic.api.v1.shared.CrudController;
import org.m.clinic.model.User;
import org.m.clinic.service.CrudService;
import org.m.clinic.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(UserController.API_URL)
public class UserController extends CrudController<User, UserDto> {

  public static final String API_URL = "/api/v1/users";

  private final UserService userService;

  @Override
  protected CrudService<User, Long> getService() {
    return userService;
  }

  @Override
  protected UserDto convertToDto(User user) {
    return UserMapper.INSTANCE.entityToDto( user );
  }

  @Override
  protected User mapDtoToEntity(UserDto requestBody, User entityToFill) {
    return UserMapper.INSTANCE.dtoToEntity( requestBody );
  }
}
