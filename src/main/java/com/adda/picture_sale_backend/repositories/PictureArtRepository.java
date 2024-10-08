package com.adda.picture_sale_backend.repositories;

import com.adda.picture_sale_backend.entities.PictureArt;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PictureArtRepository extends MongoRepository<PictureArt, Long> {



}
