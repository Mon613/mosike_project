package org.example.mosike_project.resource.user;

import org.example.mosike_project.dto.SongDTO;
import org.example.mosike_project.entities.Song;
import org.example.mosike_project.mapper.SongMapper;
import org.example.mosike_project.repositories.SongRepository;
import org.example.mosike_project.services.ISong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/songs")
public class SongsRest {
    @Autowired
    private ISong iSong;
    @Autowired
    private SongRepository songRepository;
    @Autowired
    private SongMapper songMapper;

    @GetMapping
    public ResponseEntity<SongDTO> getSongByName(@RequestParam("id") Long id){
        SongDTO song = songMapper.toDto(songRepository.findById(id).get());
        return ResponseEntity.ok(song);
    }
//@GetMapping
//public Song getSongByName(@RequestParam("id") Long id){
//
//    return iSong.findById(id);
//}
}
