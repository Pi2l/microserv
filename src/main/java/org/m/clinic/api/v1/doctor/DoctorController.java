package org.m.clinic.api.v1.doctor;

import lombok.RequiredArgsConstructor;
import org.m.clinic.api.v1.shared.CrudController;
import org.m.clinic.model.Doctor;
import org.m.clinic.service.CrudService;
import org.m.clinic.service.DoctorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping(DoctorController.API_URL)
public class DoctorController extends CrudController<Doctor, DoctorDto> {

  public static final String API_URL = "/api/v1/doctors";

  private final DoctorService service;

  @Override
  protected CrudService<Doctor, Long> getService() {
    return service;
  }

  @Override
  protected DoctorDto convertToDto(Doctor doctor) {
    return DoctorMapper.INSTANCE.entityToDto( doctor );
  }

  @Override
  protected Doctor mapDtoToEntity(DoctorDto dto, Doctor entityToFill) {
    dto.setId( entityToFill.getId() );
    return DoctorMapper.INSTANCE.dtoToEntity( dto );
  }
}
