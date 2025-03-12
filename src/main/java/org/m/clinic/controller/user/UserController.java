package org.m.clinic.controller.user;

import org.m.clinic.controller.shared.CrudController;
import org.m.clinic.model.User;
import org.m.clinic.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UserController.USER_API)
public class UserController extends CrudController<User, UserService, UserDto> {

  public static final String USER_API = "/api/v1/users";

  public UserController(UserService userService) {
    super(userService);
  }

  @Override
  protected UserDto convertToDto(User user) {
    var dto = new UserDto();
    dto.setId(user.getId());
    dto.setEmail(user.getEmail());
    dto.setFirstName(user.getFirstName());
    dto.setLastName(user.getLastName());
    dto.setRole(user.getRole());
    return dto;
  }

  @Override
  protected User mapDtoToEntity(UserDto requestBody, User entityToFill) {
    entityToFill.setEmail(requestBody.getEmail());
    entityToFill.setPassword(requestBody.getPassword());
    entityToFill.setFirstName(requestBody.getFirstName());
    entityToFill.setLastName(requestBody.getLastName());
    entityToFill.setRole(requestBody.getRole());
    return entityToFill;
  }
}
