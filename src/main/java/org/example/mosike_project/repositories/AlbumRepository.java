package org.example.mosike_project.repositories;

import org.example.mosike_project.dto.AlbumDTO;
import org.example.mosike_project.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album,Long> {
    List<Album> findByNameAlbum(String txt);
}
