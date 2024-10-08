package com.adda.picture_sale_backend.repositories;

import com.adda.picture_sale_backend.entities.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, Long> {
    List<Category> findAllByLanguage(String language);

}
