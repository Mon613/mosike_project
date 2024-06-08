package org.example.mosike_project.controller.user;

import org.example.mosike_project.dto.AlbumDTO;
import org.example.mosike_project.services.IAlbum;
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
@RequestMapping("user/albums")
public class AlbumUserController {
    @Autowired
    private IAlbum iAlbum;
    @GetMapping("/album")
    private String getAlbums(Model model, @RequestParam("page") Optional<Integer> page){
        int pageIndex = page.orElse(1);
        long seed = LocalDate.now().toEpochDay();
        Page<AlbumDTO> albumDTOPage = iAlbum.findAll(PageRequest.of(pageIndex-1,20));
        List<AlbumDTO> list = albumDTOPage.getContent();
        //album moi
        List<AlbumDTO> randomAlbums = new ArrayList<>(list);
        Collections.shuffle(randomAlbums, new Random(seed));
//        randomAlbums = randomAlbums.subList(0, 5);
        //album cho ban
        List<AlbumDTO> recommendedAlbums  = new ArrayList<>(list);
        Collections.shuffle(recommendedAlbums , new Random(seed));
//        recommendedAlbums  = recommendedAlbums .subList(0, 5);

        model.addAttribute("total_page",albumDTOPage.getTotalPages());
        model.addAttribute("this_page",pageIndex);
        model.addAttribute("recommendedAlbums",recommendedAlbums);
        model.addAttribute("new_album",randomAlbums);
        return "user/album/albums";
    }
}
