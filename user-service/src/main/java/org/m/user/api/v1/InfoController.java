package org.m.user.api.v1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(InfoController.API_URL)
@RequiredArgsConstructor
public class InfoController {

  public static final String API_URL = "/api/v1/info";

  @Value("${project.title}")
  private String projectTitle;

  @RequestMapping
  public String info() {
    return projectTitle;
  }

}
