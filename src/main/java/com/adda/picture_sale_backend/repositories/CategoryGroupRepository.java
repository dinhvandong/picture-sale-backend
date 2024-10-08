package com.adda.picture_sale_backend.repositories;

import com.adda.picture_sale_backend.entities.CategoryGroup;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryGroupRepository extends MongoRepository<CategoryGroup, Long> {

   // List<CategoryGroup> findAllByUserID(Long userID);



}
