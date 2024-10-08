package com.adda.picture_sale_backend.services;


import com.adda.picture_sale_backend.database.SequenceGeneratorService;
import com.adda.picture_sale_backend.entities.Category;
import com.adda.picture_sale_backend.entities.PictureArt;
import com.adda.picture_sale_backend.repositories.CategoryRepository;
import com.adda.picture_sale_backend.repositories.PictureArtRepository;
import com.adda.picture_sale_backend.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PictureArtService {

    @Autowired
    PictureArtRepository pictureArtRepository;
    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    public List<PictureArt> findAll() {
        return pictureArtRepository.findAll();
    }

    public PictureArt create(PictureArt news) {
        Long id = sequenceGeneratorService.generateSequence(PictureArt.SEQUENCE_NAME);
        news.setId(id);
        // news.setActive(true);
        //news.setCreatedDate(DateUtils.getCurrentDate());
        return pictureArtRepository.insert(news);
    }

    public boolean delete(Long id) {

        pictureArtRepository.deleteById(id);
        return true;
    }

    public boolean deleteAll() {
        pictureArtRepository.deleteAll();
        return true;
    }

    public PictureArt findById(Long id) {
        Optional<PictureArt> optional = pictureArtRepository.findById(id);
        if (optional.isEmpty()) return null;
        return optional.get();
    }

    public PictureArt update(PictureArt news) {
        Optional<PictureArt> optional = pictureArtRepository.findById(news.getId());
        if (optional.isEmpty()) return null;
        PictureArt found = optional.get();
        found.setName(news.getName());
        //found.setDesc(news.getDesc());
        return pictureArtRepository.save(found);
    }





}
