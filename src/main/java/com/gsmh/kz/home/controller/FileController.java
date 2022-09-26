package com.gsmh.kz.home.controller;

import com.gsmh.kz.home.model.dto.FileResponse;
import com.gsmh.kz.home.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@AllArgsConstructor
@CrossOrigin
public class FileController{

  private FileService fileService;

  @PostMapping("/uploadSingleFile")
  public ResponseEntity<FileResponse> uploadSingleFile(@RequestParam(name = "file") MultipartFile file){
    return ResponseEntity.ok(fileService.saveSingleFile(file));
  }

  @PostMapping("/uploadFileBase64")
  public ResponseEntity<FileResponse> uploadSingleFileBase64(@RequestParam String fileBase64){
    return ResponseEntity.ok(fileService.saveSingleFileBase64(fileBase64));
  }
}
