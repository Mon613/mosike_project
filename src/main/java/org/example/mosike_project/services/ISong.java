package org.example.mosike_project.services;


import org.example.mosike_project.dto.SongDTO;
import org.example.mosike_project.entities.Song;
import org.example.mosike_project.model.SongModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ISong {
    List<SongDTO> getAll();
    int save(SongModel songModel);
    List<Song> findByName(String txt);
    void update(SongModel songModel);
    int deleteById(Long id);
    Page<SongDTO> findAllPage(Pageable pageable);
    Song findById(Long id);



}
