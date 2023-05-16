package com.suryansh.Controller;

import com.suryansh.Service.impl.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/image")
@CrossOrigin("*")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload/{username}")
    public String uploadImage(@RequestParam("image") MultipartFile file, @PathVariable String username) {
        return imageService.saveImage(file, username);
    }

    @GetMapping("/get-by-name/{name}")
    public ResponseEntity<byte[]> getImageByName(@PathVariable String name) throws IOException {
        byte[] image = imageService.getImage(name);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
    }

    @PutMapping("/update/{fileName}")
    public String updateUserProfilePic(@PathVariable String fileName,
                                       @RequestParam("image") MultipartFile file) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return imageService.updateImage(fileName, file, username);
    }
}
