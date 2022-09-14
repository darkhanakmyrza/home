package com.gsmh.kz.home.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FileResponse {
  private String filename;
  private String fileDownloadUri;
}
