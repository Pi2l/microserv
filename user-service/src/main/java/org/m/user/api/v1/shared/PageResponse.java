package org.m.user.api.v1.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PageResponse<Dto> {
  List<Dto> items;
  long totalElements;
  long index;
  long size;
}
