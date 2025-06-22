package org.m.user.model;

import org.m.lib.model.Auditable;
import org.m.lib.model.HasIdentifier;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "medical_record")
@Getter
@Setter
@NoArgsConstructor
public class MedicalRecord extends Auditable implements HasIdentifier {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "patient_id")
  private Patient patient;

  @ManyToOne
  @JoinColumn(name = "doctor_id")
  private Doctor doctor;

  @Column(nullable = false)
  private String diagnosis;

  @Column(nullable = false)
  private String prescription;

}
