package org.m.availableslot.kafka;

import org.m.availableslot.model.AvailableSlot;
import org.m.availableslot.service.AvailableSlotService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AppointmentListener {

  private final AvailableSlotService availableSlotService;

  @KafkaListener(topics = "appointments", groupId = "availableSlotGroupId")
  public void listen(Object message) {
    System.out.println("Received message: " + message);
    
    // availableSlotService.create( ))
  }
}
