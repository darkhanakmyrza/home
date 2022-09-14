package com.gsmh.kz.home.controller;

import com.gsmh.kz.home.model.dto.FileResponse;
import com.gsmh.kz.home.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@AllArgsConstructor
public class FileController{

  private FileService fileService;

  @PostMapping("/uploadSingleFile")
  public ResponseEntity<FileResponse> uploadSingleFile(@RequestParam(name = "file") MultipartFile file){
    return ResponseEntity.ok(fileService.saveSingleFile(file));
  }
}
