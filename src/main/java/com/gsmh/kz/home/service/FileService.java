package com.gsmh.kz.home.service;

import com.gsmh.kz.home.config.security.jwt.AuthTokenFilter;
import com.gsmh.kz.home.exception.FileStorageException;
import com.gsmh.kz.home.model.dto.FileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.YearMonth;
import java.util.UUID;

@Service
public class FileService {

  private static final Logger logger = LoggerFactory.getLogger(FileService.class);

  @Value("${file.upload-dir}")
  private String uploadDir;

  @Value("${file.download-uri}")
  private String fileDownloadUri;

  public FileResponse saveSingleFile(MultipartFile file) {
    String folder = YearMonth.now().getYear() + "-" + YearMonth.now().getMonthValue();
    String filename = storeFile(file, folder);
    String fileDownloadUrl = ServletUriComponentsBuilder.fromHttpUrl(fileDownloadUri)
        .path("/" + folder + "/")
        .path(filename)
        .toUriString();
    return new FileResponse(filename, fileDownloadUrl);
  }

  private String storeFile(MultipartFile file, String folder) {
    String filename = UUID.randomUUID().toString() + ".webp";

    Path fileStorageLocation = Paths.get(uploadDir + "/" + folder).toAbsolutePath().normalize();
    try {
      try {
        Files.createDirectories(fileStorageLocation);
      } catch (Exception ex) {
        logger.error("Could not create the directory where the uploaded files will be stored." + ex.getMessage());
        throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
      }
      Path targetLocation = fileStorageLocation.resolve(filename);
      Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
      return filename;
    } catch (IOException ex) {
      throw new FileStorageException("Could not store file $fileName. Please try again!", ex);
    }
  }
}
