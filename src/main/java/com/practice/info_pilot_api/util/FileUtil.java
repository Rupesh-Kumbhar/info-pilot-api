package com.practice.info_pilot_api.util;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {

    public static String saveFile(MultipartFile file,String uploadDir)throws IOException {

        Path uploadPath =Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName =System.currentTimeMillis()+ "_"+ file.getOriginalFilename();
        Path filePath =uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(),filePath);

        return filePath.toString();
    }
}