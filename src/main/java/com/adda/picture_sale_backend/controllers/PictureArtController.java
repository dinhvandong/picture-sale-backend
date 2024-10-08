package com.adda.picture_sale_backend.controllers;

import com.adda.picture_sale_backend.dto.ResponseObject;
import com.adda.picture_sale_backend.entities.Category;
import com.adda.picture_sale_backend.entities.PictureArt;
import com.adda.picture_sale_backend.jwt.JwtInterceptor;
import com.adda.picture_sale_backend.services.CategoryService;
import com.adda.picture_sale_backend.services.PictureArtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/pictureArt")
public class PictureArtController {

    @Autowired
    PictureArtService pictureArtService;
    @PostMapping("/findAll")
    public ResponseEntity<?> findAll()
    {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, pictureArtService.findAll(),"success"));


    }


    @PostMapping("/findById")
    public ResponseEntity<?> findById(@RequestParam Long id)
    {
        PictureArt item = null;
        item = pictureArtService.findById(id);
        if(item!= null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, item,"success"));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"fail"));
        }
    }
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestParam String token, @RequestBody PictureArt item)
    {
        if(token.isBlank())
        {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"transaction is not exist"));
        }
        token = "Bearer " + token;
        boolean isAuthenticated = JwtInterceptor.getInstance().isValidToken(token);
        if(isAuthenticated)
        {
            PictureArt response =  pictureArtService.update(item);
            if(response!= null)
            {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, response,"category is not exist"));
            }
            else
            {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"category is not exist"));
            }
        }
        else
        {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"category is not exist"));
        }
    }

    @PostMapping("/deleteAll")
    public ResponseEntity<?> deleteAll()
    {
        boolean check =  pictureArtService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, check,"success"));

    }
    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam String token, @RequestParam Long id)
    {
        if(token.isBlank())
        {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"token is not exist"));
        }
        token = "Bearer " + token;

        boolean isAuthenticated = JwtInterceptor.getInstance().isValidToken(token);
        if(isAuthenticated)
        {
            // newNotification.setSenderAccount(userFound.getUsername());
            PictureArt item = pictureArtService.findById(id);
            if(item!=null){
                pictureArtService.delete(id);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "OK","success"));
            }else {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(220, "Fail","Not found"));
            }
        }
        else
        {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"token is not exist"));
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestParam String token,
                                    @RequestBody PictureArt item )
    {
        if(token.isBlank())
        {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"transaction is not exist"));
        }
        token = "Bearer " + token;
        boolean isAuthenticated = JwtInterceptor.getInstance().isValidToken(token);
        if(isAuthenticated)
        {
            PictureArt response =  pictureArtService.create(item);
            if(response!= null)
            {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, response,"category is added "));
            }
            else
            {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"category is not exist"));
            }
        }
        else
        {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"category is not exist"));
        }
    }
}
