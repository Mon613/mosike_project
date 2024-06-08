package org.example.mosike_project.resource;

import org.example.mosike_project.dto.SongDTO;
import org.example.mosike_project.entities.Song;
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

    @GetMapping
    public ResponseEntity<List<Song>> getSongByName(@RequestParam("title") String title){
        String decodedTitle = URLDecoder.decode(title, StandardCharsets.UTF_8);
        List<Song> song = iSong.findByName(decodedTitle);
        return ResponseEntity.ok(song);
    }
//@GetMapping
//public Song getSongByName(@RequestParam("id") Long id){
//
//    return iSong.findById(id);
//}
}
