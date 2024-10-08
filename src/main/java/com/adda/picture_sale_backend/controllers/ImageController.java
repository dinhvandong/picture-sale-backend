package com.adda.picture_sale_backend.controllers;

import com.adda.picture_sale_backend.dto.ResponseObject;
import com.adda.picture_sale_backend.entities.Image;
import com.adda.picture_sale_backend.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@CrossOrigin(origins = {
        "http://163.44.206.118:83",
        "http://163.44.206.118:80",
        "http://163.44.206.118",
        "http://163.44.206.118:81",
        "http://localhost:3001",
        "http://localhost:3000",
        "http://150.95.113.18",
        "http://ambassadordaycruise.com/",
        "http://admin.ambassadordaycruise.com/"

})
@RestController
@RequestMapping("/api/images")
public class ImageController {
    private final ImageService imageService;
    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }
    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
//        try {
//            if(token.isBlank()){
//                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"user not exist"));
//            }
//            token = "Bearer " + token;
//            boolean isAuthenticated = JwtInterceptor.getInstance().isValidToken(token);
//            if(isAuthenticated){
//                          }else {
//               return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"upload not Ok"));
//            }
//        } catch (IOException e) {
//           return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"upload not Ok"));
//        }

        Image savedImage = imageService.saveImage(file);
        //return ResponseEntity.ok(savedImage.getId());
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, savedImage.getId(),"upload Ok"));

    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) {
        Optional<Image> imageOptional = imageService.getImage(id);
        if (imageOptional.isPresent()) {
            Image image = imageOptional.get();
            //return ResponseEntity.ok().body(image.getData());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // Set the appropriate media type, e.g., IMAGE_JPEG, IMAGE_PNG, etc.

            // Return the image byte array with the headers
            return new ResponseEntity<>(image.getData(), headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}