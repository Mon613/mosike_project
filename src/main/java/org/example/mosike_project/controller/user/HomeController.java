package org.example.mosike_project.controller.user;

import org.example.mosike_project.dto.AlbumDTO;
import org.example.mosike_project.dto.CategoryDTO;
import org.example.mosike_project.dto.SingerDTO;
import org.example.mosike_project.dto.SongDTO;
import org.example.mosike_project.entities.Category;
import org.example.mosike_project.mapper.AlbumMapper;
import org.example.mosike_project.mapper.CategoryMapper;
import org.example.mosike_project.model.AlbumModel;
import org.example.mosike_project.model.SongModel;
import org.example.mosike_project.repositories.AlbumRepository;
import org.example.mosike_project.services.IAlbum;
import org.example.mosike_project.services.ICategory;
import org.example.mosike_project.services.ISinger;
import org.example.mosike_project.services.ISong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("user")
public class HomeController {
    @Autowired
    private ICategory iCategory;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ISong iSong;
    @Autowired
    private IAlbum iAlbum;
    @Autowired
    private ISinger iSinger;
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private AlbumMapper albumMapper;

    @GetMapping("home")
    private String findAll(Model model, @RequestParam("page") Optional<Integer> page) {
        int pageIndex = page.orElse(1);
        Page<SongDTO> songDTOPage = iSong.findAllPage(PageRequest.of(pageIndex - 1, 6));
        Page<AlbumDTO> albumDTOPage = iAlbum.findAll(PageRequest.of(pageIndex-1,5));
        Page<SingerDTO> singerDTOS = iSinger.findAllPage(PageRequest.of(pageIndex - 1, 5));
        model.addAttribute("total_page", songDTOPage.getTotalPages());
        model.addAttribute("this_page", pageIndex);
        model.addAttribute("song_list", songDTOPage.getContent());
        model.addAttribute("total_page",albumDTOPage.getTotalPages());
        model.addAttribute("album_list",albumDTOPage.getContent());
        model.addAttribute("singer_list", singerDTOS.getContent());
        model.addAttribute("total_page", singerDTOS.getTotalPages());
        return "user/home";
    }
    @GetMapping("songByAlbum/{id}")
    private String findALbum(Model model, @PathVariable("id") Long id){
        List<AlbumDTO> albumDTO = albumRepository.findById(id).stream().map(e->albumMapper.toDto(e)).toList();
        model.addAttribute("albums",albumDTO);
        return "user/album/songByAlbum";
    }
}
