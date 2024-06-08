package org.example.mosike_project.controller.user;

import org.example.mosike_project.dto.SongDTO;
import org.example.mosike_project.services.ISong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("user/songs")
public class SongsUserController {
    @Autowired
    private ISong iSong;

    @GetMapping("song")
    private String findAll(Model model, @RequestParam("page") Optional<Integer> page) {
        int pageIndex = page.orElse(1);
        long seed = LocalDate.now().toEpochDay();
        Page<SongDTO> songDTOPage = iSong.findAllPage(PageRequest.of(pageIndex - 1, 50));
        List<SongDTO> allSongs = songDTOPage.getContent();
        List<SongDTO> randomSongs = new ArrayList<>(allSongs);
        Collections.shuffle(randomSongs,new Random(seed));
        model.addAttribute("total_page", songDTOPage.getTotalPages());
        model.addAttribute("this_page", pageIndex);
        model.addAttribute("song_list", randomSongs);
        return "user/allSong";
    }
}
