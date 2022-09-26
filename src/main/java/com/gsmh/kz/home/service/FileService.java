package com.gsmh.kz.home.service;

import com.gsmh.kz.home.exception.FileStorageException;
import com.gsmh.kz.home.model.dto.FileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.YearMonth;
import java.util.Base64;
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

    public FileResponse saveSingleFileBase64(String fileBase64) {
        String folder = YearMonth.now().getYear() + "-" + YearMonth.now().getMonthValue();
        String filename = storeFileBase64(folder, fileBase64);
        String fileDownloadUrl = ServletUriComponentsBuilder.fromHttpUrl(fileDownloadUri)
            .path("/" + folder + "/")
            .path(filename)
            .toUriString();
        return new FileResponse(filename, fileDownloadUrl);
    }

    private String storeFileBase64(String folder, String fileBase64) {
        String filename = UUID.randomUUID().toString() + ".webp";

        try {
            // Check if the file's name contains invalid characters
            if (filename.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + filename);
            }
            // Copy file to the target location (Replacing existing file with the same name)
            Path fileStorageLocation = Paths.get(uploadDir + "/" + folder).toAbsolutePath().normalize();
            try {
                Files.createDirectories(fileStorageLocation);
            } catch (Exception ex) {
                logger.error("Could not create the directory where the uploaded files will be stored." +  ex.getMessage());
                throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
            }
            Path targetLocation = fileStorageLocation.resolve(filename);
            fileBase64 = fileBase64.replaceAll(" ", "+");
            Files.copy(new ByteArrayInputStream(Base64.getDecoder().decode(fileBase64)), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return filename;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + filename + ". Please try again!", ex);
        }
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
