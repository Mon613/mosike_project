package org.example.mosike_project.services;

import org.example.mosike_project.dto.SingerDTO;
import org.example.mosike_project.entities.Singer;
import org.example.mosike_project.model.SingerModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISinger {
    List<SingerDTO> getAll();
    int save(SingerModel singerModel);
    List<Singer> findByNameSinger(String txt);
    void update(SingerModel singerModel);
    int deleteById(Long id);
    Page<SingerDTO> findAllPage(Pageable pageable);
}
