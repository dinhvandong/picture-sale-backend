package com.adda.picture_sale_backend.services;


import com.adda.picture_sale_backend.database.SequenceGeneratorService;
import com.adda.picture_sale_backend.entities.Artist;
import com.adda.picture_sale_backend.entities.PictureArt;
import com.adda.picture_sale_backend.repositories.ArtistRepository;
import com.adda.picture_sale_backend.repositories.PictureArtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    @Autowired
    ArtistRepository artistRepository;
    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    public List<Artist> findAll() {
        return artistRepository.findAll();
    }

    public Artist create(Artist item) {
        Long id = sequenceGeneratorService.generateSequence(PictureArt.SEQUENCE_NAME);
        item.setId(id);
        // news.setActive(true);
        //news.setCreatedDate(DateUtils.getCurrentDate());
        return artistRepository.insert(item);
    }

    public boolean delete(Long id) {

        artistRepository.deleteById(id);
        return true;
    }

    public boolean deleteAll() {
        artistRepository.deleteAll();
        return true;
    }

    public Artist findById(Long id) {
        Optional<Artist> optional = artistRepository.findById(id);
        if (optional.isEmpty()) return null;
        return optional.get();
    }

    public Artist update(Artist news) {
        Optional<Artist> optional = artistRepository.findById(news.getId());
        if (optional.isEmpty()) return null;
        Artist found = optional.get();
        found.setName(news.getName());
        //found.setDesc(news.getDesc());
        return artistRepository.save(found);
    }

}
