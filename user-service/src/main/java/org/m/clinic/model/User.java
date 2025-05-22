package org.m.clinic.model;

import jakarta.persistence.*;
import lombok.*;

import org.m.lib.model.HasIdentifier;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends Auditable implements HasIdentifier {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Enumerated(EnumType.STRING)
  @Column(length = 30)
  private Role role;

  public User(Long id) {
    this.id = id;
  }

}
