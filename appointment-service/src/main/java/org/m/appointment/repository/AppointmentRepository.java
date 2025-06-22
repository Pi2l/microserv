package org.m.appointment.repository;

import org.m.appointment.model.Appointment;
import org.m.lib.repository.PrimaryRepository;

public interface AppointmentRepository extends PrimaryRepository<Appointment, Long> {
}
