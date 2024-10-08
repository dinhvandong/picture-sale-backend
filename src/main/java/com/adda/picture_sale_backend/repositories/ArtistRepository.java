package com.adda.picture_sale_backend.repositories;

import com.adda.picture_sale_backend.entities.Artist;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArtistRepository extends MongoRepository<Artist, Long> {
}
