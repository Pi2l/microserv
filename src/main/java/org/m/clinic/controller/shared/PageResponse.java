package org.m.clinic.controller.shared;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PageResponse<Dto> {
  List<Dto> items;
  long totalElements;
  long index;
  long size;
}
