package org.m.user.repository;

import java.util.Optional;
import org.m.lib.repository.PrimaryRepository;
import org.m.user.model.User;

public interface UserRepository extends PrimaryRepository<User, Long> {

  Optional<User> findByEmail(String email);
}
