package org.m.clinic.api.v1.patient;

import lombok.RequiredArgsConstructor;
import org.m.clinic.api.v1.shared.CrudController;
import org.m.clinic.model.Patient;
import org.m.lib.service.CrudService;
import org.m.clinic.service.PatientService;
import org.m.clinic.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping(PatientController.API_URL)
public class PatientController extends CrudController<Patient, PatientDto> {

  public static final String API_URL = "/api/v1/patients";

  private final PatientService patientService;
  private final UserService userService;

  @Override
  protected CrudService<Patient, Long> getService() {
    return patientService;
  }

  @Override
  protected PatientDto convertToDto(Patient patient) {
    return PatientMapper.INSTANCE.entityToDto(patient);
  }

  @Override
  protected Patient mapDtoToEntity(PatientDto dto, Patient entityToFill) {
    dto.setId( entityToFill.getId() );
    return PatientMapper.INSTANCE.dtoToEntity(dto);
  }

  @Override
  protected void beforeEntityUpdate(Patient entity) {
    var user = entity.getUser();
    Long userId = entity.getId();
    if (user != null && user.getId() != null) {
      userId = user.getId();
    }
    entity.setUser( getEntity(userId, userService, false) );
  }
}
