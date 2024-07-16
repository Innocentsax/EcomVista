package dev.Innocent.service.Impl;

import dev.Innocent.repository.MovieRepository;
import dev.Innocent.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final MovieRepository movieRepository;

    @Override
    public String uploadFile(String path, MultipartFile file) throws IOException {
        // Get name of file
        String fileName = file.getOriginalFilename();
        // Get the file path
        String filePath = path + File.separator + fileName;
        // Create file object
        File fileObject = new File(path);
        if(!fileObject.exists()){
            fileObject.mkdirs();
        }
        // Copy file or upload file to the path
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }

    @Override
    public InputStream getResourceFile(String path, String filename) throws FileNotFoundException {
        String filePath = path + File.separator + filename;
        return new FileInputStream(filePath);
    }
}
