package org.example.mosike_project.repositories;

import org.example.mosike_project.dto.SongDTO;
import org.example.mosike_project.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song,Long> {
    List<Song> findByNameSong(String txt);


}
