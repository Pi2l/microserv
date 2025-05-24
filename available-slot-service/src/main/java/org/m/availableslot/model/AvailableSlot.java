package org.m.availableslot.model;

import java.time.ZonedDateTime;

import org.m.lib.model.Auditable;
import org.m.lib.model.HasIdentifier;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "available_slots")
@Getter
@Setter
@NoArgsConstructor
public class AvailableSlot extends Auditable implements HasIdentifier {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "doctor_id", nullable = false)
  private Long doctorId;

  @Column(nullable = false, name = "slot_time_start")
  private ZonedDateTime slotTimeStart;

  @Column(nullable = false)
  private Long duration;

  @Column(nullable = false, name = "is_booked")
  private Boolean isBooked = false;

}
