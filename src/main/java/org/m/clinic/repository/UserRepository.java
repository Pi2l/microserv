package org.m.clinic.repository;

import org.m.clinic.model.User;

import java.util.Optional;

public interface UserRepository extends PrimaryRepository<User, Long> {

  Optional<User> findByLogin(String login);
}
