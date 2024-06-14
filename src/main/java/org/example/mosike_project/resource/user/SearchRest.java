package org.example.mosike_project.resource.user;

import org.example.mosike_project.dto.SongDTO;
import org.example.mosike_project.entities.Song;
import org.example.mosike_project.mapper.SongMapper;
import org.example.mosike_project.services.ISong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("search")
public class SearchRest {
    @Autowired
    private ISong iSong;
    @Autowired
    private SongMapper songMapper;

    @GetMapping("/searchSong")
    private ResponseEntity<List<SongDTO>> findByNameSong(@RequestParam("txt")String txt){
        List<SongDTO> songList = iSong.findByName(txt).stream().map(e->songMapper.toDto(e)).toList();
        return new ResponseEntity<>(songList, HttpStatus.OK);
    }
}
