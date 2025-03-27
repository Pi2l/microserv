package org.m.clinic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Doctor extends Auditable implements HasIdentifier {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  @JoinColumn(name = "id")
  private User user;

  @Column(name = "specialization")
  private String specialization;

  @Column(name = "experience_years")
  private int experienceYears;
}
