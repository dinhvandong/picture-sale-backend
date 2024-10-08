//package com.adda.picture_sale_backend.controllers;
//
//import com.adda.picture_sale_backend.dto.ResponseObject;
//import com.adda.picture_sale_backend.entities.CategoryGroup;
//import com.adda.picture_sale_backend.jwt.JwtInterceptor;
//import com.adda.picture_sale_backend.services.CategoryGroupService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@CrossOrigin(origins = {
//        "http://163.44.206.118:83",
//        "http://163.44.206.118:80",
//        "http://163.44.206.118",
//        "http://163.44.206.118:81",
//        "http://localhost:3001",
//        "http://localhost:3000",
//        "http://150.95.113.18",
//        "http://ambassadordaycruise.com/",
//        "http://admin.ambassadordaycruise.com/"
//
//})
//@RestController
//@RequestMapping("/api/category-group")
//public class CategoryGroupController {
//    @Autowired
//    CategoryGroupService categoryGroupService;
//    @PostMapping("/findAll")
//    public ResponseEntity<?> findAll(@RequestParam String token)
//    {
//        if(token.isBlank()){
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"user not exist"));
//        }
//        token = "Bearer " + token;
//
//        boolean isAuthenticated = JwtInterceptor.getInstance().isValidToken(token);
//        if(isAuthenticated){
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, categoryGroupService.findAll(),"success"));
//        }else {
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"user not exist"));
//        }
//    }
//
//    @PostMapping("/insert")
//    public ResponseEntity<?> insert(@RequestParam String token, @RequestBody CategoryGroup group)
//    {
//        if(token.isBlank()){
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"group not exist"));
//        }
//        token = "Bearer " + token;
//
//        boolean isAuthenticated = JwtInterceptor.getInstance().isValidToken(token);
//        if(isAuthenticated){
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, categoryGroupService.insert(group),"success"));
//        }else {
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"group not exist"));
//        }
//    }
//
//
//    @PostMapping("/update")
//    public ResponseEntity<?> update(@RequestParam String token, @RequestBody CategoryGroup group)
//    {
//        if(token.isBlank()){
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"group not exist"));
//        }
//        token = "Bearer " + token;
//
//        boolean isAuthenticated = JwtInterceptor.getInstance().isValidToken(token);
//        if(isAuthenticated){
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, categoryGroupService.update(group),"success"));
//        }else {
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"group not exist"));
//        }
//    }
//
//    @PostMapping("/findById")
//    public ResponseEntity<?> findById(@RequestParam String token, @RequestParam Long id)
//    {
//        if(token.isBlank())
//        {
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"transaction is not exist"));
//        }
//        token = "Bearer " + token;
//        boolean isAuthenticated = JwtInterceptor.getInstance().isValidToken(token);
//        if(isAuthenticated)
//        {
//            CategoryGroup response =  categoryGroupService.findById(id);
//            if(response!= null)
//            {
//                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, response,"category group is not exist"));
//            }
//            else
//            {
//                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"category group is not exist"));
//            }
//        }
//        else
//        {
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"category group is not exist"));
//        }
//    }
//
//
//
//}
