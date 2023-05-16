package com.suryansh.Service.impl;

import com.suryansh.Exception.SpringShowException;
import com.suryansh.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class ImageService {
    private final static Logger logger = LoggerFactory.getLogger(ImageService.class);
    private final UserRepository userRepository;
    @Value("${imageFolderPath}")
    private String Folder_Path;

    public ImageService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String saveImage(MultipartFile file, String username) {
        if (file.isEmpty()) throw new SpringShowException("You haven't provided any image");
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new SpringShowException("Unable to find user " + username));
        if (user.getUserPic() != null) throw new SpringShowException("User pic is already uploaded !!");
        user.setUserPic(file.getOriginalFilename());
        String targetFilePath = Folder_Path + "/" + file.getOriginalFilename();
        File targetFile = new File(targetFilePath);
        try {
            file.transferTo(targetFile);
            user.setUserPic(file.getOriginalFilename());
            userRepository.save(user);
            logger.info("User {} profile pic is uploaded.", user.getUsername());
            return "Profile pic is uploaded successfully!";
        } catch (Exception e) {
            logger.error("Unable to upload profile pic for {}.", user.getUsername(), e);
            throw new SpringShowException("Profile pic is not uploaded!");
        }
    }

    public byte[] getImage(String name) throws IOException {
        String fullPath = Folder_Path + "/" + name;
        File file = new File(fullPath);
        if (!file.exists()) throw new SpringShowException("Unable to find image of name " + name);
        return Files.readAllBytes(file.toPath());
    }

    public String updateImage(String fileName, MultipartFile file, String username) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new SpringShowException("Unable to find user " + username));
        if (user.getUserPic() == null) {
            return saveImage(file, username);
        }
        String fullPath = Folder_Path + "/" + fileName;
        File oldFile = new File(fullPath);
        if (!oldFile.exists()) throw new SpringShowException("Unable to find image of name " + fileName);
        user.setUserPic(file.getOriginalFilename());
        try {
            if (oldFile.delete()) {
                logger.info("Image {} is deleted for user {} ", fileName, username);
            }
            String targetFilePath = Folder_Path + "/" + file.getOriginalFilename();
            File targetFile = new File(targetFilePath);
            file.transferTo(targetFile);
            userRepository.save(user);
            logger.info("Profile pic is updated for user {}", username);
            return "Profile pic is updated";
        } catch (Exception e) {
            logger.error("Profile pic is not updated for user {}", username);
            throw new SpringShowException("Unable to update Profile Pic for user " + username);
        }
    }
}
