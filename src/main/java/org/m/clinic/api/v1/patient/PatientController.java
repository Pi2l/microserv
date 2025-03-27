package org.m.clinic.api.v1.patient;

import lombok.RequiredArgsConstructor;
import org.m.clinic.api.v1.shared.CrudController;
import org.m.clinic.model.Patient;
import org.m.clinic.service.CrudService;
import org.m.clinic.service.PatientService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.m.clinic.model.User;


@RequiredArgsConstructor
@RestController
@RequestMapping(PatientController.API_URL)
public class PatientController extends CrudController<Patient, PatientDto> {

  public static final String API_URL = "/api/v1/patients";

  private final PatientService patientService;

  @Override
  protected CrudService<Patient, Long> getService() {
    return patientService;
  }

  @Override
  protected PatientDto convertToDto(Patient patient) {
    var dto = new PatientDto();
    dto.setId(patient.getId());
    var user = patient.getUser();
    if (user != null) {
      dto.setUserId(user.getId());
    }
    dto.setPhoneNumber(patient.getPhoneNumber());
    dto.setDateOfBirth(patient.getDateOfBirth());
    return dto;
  }

  @Override
  protected Patient mapDtoToEntity(PatientDto dto, Patient entityToFill) {
    var user = User.builder().id(dto.getId()).build();

    entityToFill.setId(dto.getId());
    entityToFill.setUser(user);
    entityToFill.setPhoneNumber(dto.getPhoneNumber());
    entityToFill.setDateOfBirth(dto.getDateOfBirth());
    return entityToFill;
  }
}
