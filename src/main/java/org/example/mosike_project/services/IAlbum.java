package org.example.mosike_project.services;

import org.example.mosike_project.dto.AlbumDTO;
import org.example.mosike_project.entities.Album;
import org.example.mosike_project.model.AlbumModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAlbum {
    Page<AlbumDTO> findAll(Pageable pageable);

    int save(AlbumModel albumModel);

    List<Album> findByName(String txt);

    void update(AlbumDTO albumDTO);

    int deleteById(Long id);
    List<AlbumDTO> getAll();


}
