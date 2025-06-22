package org.m.availableslot.service;

import org.m.availableslot.api.v1.shared.ItemNotFoundException;
import org.m.availableslot.api.v1.user.DoctorDto;
import org.m.availableslot.model.AvailableSlot;
import org.m.availableslot.repository.AvailableSlotRepository;
import org.m.lib.repository.PrimaryRepository;
import org.m.lib.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AvailableSlotService extends CrudService<AvailableSlot, Long> {

  private final AvailableSlotRepository repository;
  private final RestTemplate restTemplate;

  @Override
  protected PrimaryRepository<AvailableSlot, Long> getRepository() {
    return repository;
  }

  @Override
  public AvailableSlot create(AvailableSlot availableSlot) {
    checkPresence(availableSlot.getDoctorId(), "http://USER/api/v1/doctors/", DoctorDto.class);
    return super.create(availableSlot);
  }

  @Override
  public AvailableSlot update(AvailableSlot availableSlot) {
    checkPresence(availableSlot.getDoctorId(), "http://USER/api/v1/doctors/", DoctorDto.class);
    return super.update(availableSlot);
  }

  private <T> void checkPresence(Long id, String url, Class<T> klass) {
    T entity = null;
    try {
      String fullUrl = restTemplate.getUriTemplateHandler().expand(url + id).toString();
      entity = restTemplate.getForObject(fullUrl, klass);
    } catch (HttpClientErrorException.NotFound ex) {
      // do nothing
    } catch (HttpClientErrorException e) {
      throw new IllegalArgumentException("Error while checking presence of entity with id " + id, e);
    }
    if (entity == null) {
      System.err.println("Entity with id " + id + " not found. Class: " + klass.getSimpleName());
      throw new ItemNotFoundException(id);
    } 
  }
}
