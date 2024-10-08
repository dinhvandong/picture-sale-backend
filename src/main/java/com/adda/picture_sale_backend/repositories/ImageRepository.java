package com.adda.picture_sale_backend.repositories;


import com.adda.picture_sale_backend.entities.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<Image, Long> {


}
