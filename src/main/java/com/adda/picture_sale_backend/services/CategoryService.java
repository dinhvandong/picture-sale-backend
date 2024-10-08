package com.adda.picture_sale_backend.services;

import com.adda.picture_sale_backend.database.SequenceGeneratorService;
import com.adda.picture_sale_backend.entities.Category;
import com.adda.picture_sale_backend.repositories.CategoryRepository;
import com.adda.picture_sale_backend.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {


    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category create(Category news) {
        Long id = sequenceGeneratorService.generateSequence(Category.SEQUENCE_NAME);
        news.setId(id);
        news.setActive(true);
        news.setCreatedDate(DateUtils.getCurrentDate());
        return categoryRepository.insert(news);
    }

    public boolean delete(Long id) {

        categoryRepository.deleteById(id);
        return true;
    }

    public boolean deleteAll() {
        categoryRepository.deleteAll();
        return true;
    }

    public Category findById(Long id) {
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isEmpty()) return null;
        return optional.get();
    }

    public Category update(Category news) {
        Optional<Category> optional = categoryRepository.findById(news.getId());
        if (optional.isEmpty()) return null;
        Category found = optional.get();
        found.setName(news.getName());
        found.setDesc(news.getDesc());
        return categoryRepository.save(found);
    }
}
