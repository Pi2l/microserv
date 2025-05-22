package org.m.clinic.repository;

import org.m.clinic.model.User;

import java.util.Optional;
import org.m.lib.repository.PrimaryRepository;

public interface UserRepository extends PrimaryRepository<User, Long> {

  Optional<User> findByEmail(String email);
}
