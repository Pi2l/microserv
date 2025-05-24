package org.m.user.service;

import lombok.AllArgsConstructor;

import org.m.lib.repository.PrimaryRepository;
import org.m.lib.service.CrudService;
import org.m.user.model.User;
import org.m.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService extends CrudService<User, Long> {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  // TODO: override update method to encode password
  @Override
  protected PrimaryRepository<User, Long> getRepository() {
    return userRepository;
  }

  public User findByEmail(String email) {
    return userRepository.findByEmail(email)
        .orElse(null);
  }
}
