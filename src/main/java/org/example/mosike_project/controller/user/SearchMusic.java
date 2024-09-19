package org.example.mosike_project.controller.user;

import org.example.mosike_project.dto.SongDTO;
import org.example.mosike_project.mapper.SongMapper;
import org.example.mosike_project.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("user")
public class SearchMusic {
    @Autowired
    private SongRepository songRepository;
    @Autowired
    private SongMapper songMapper;
    @GetMapping("/search")
    private String searchSongs(){
        return "user/search/search";
    }
    @GetMapping("/searchs")
    private String searchSong(Model model, @RequestParam("txt")String txt){
        List<SongDTO> songList = songRepository.findByNameSongContainingIgnoreCase(txt).stream().map(e->songMapper.toDto(e)).toList();
        model.addAttribute("songs",songList);
        return "user/search/search";
    }
}
