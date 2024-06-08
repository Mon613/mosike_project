package org.example.mosike_project.controller.admin;

import org.example.mosike_project.dto.AlbumDTO;
import org.example.mosike_project.dto.SongDTO;
import org.example.mosike_project.entities.Album;
import org.example.mosike_project.entities.Song;
import org.example.mosike_project.model.AlbumModel;
import org.example.mosike_project.services.IAlbum;
import org.example.mosike_project.services.ISong;
import org.example.mosike_project.services.UploadService.GoogleCloundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/albums")
public class AlbumController {
    @Autowired
    private IAlbum iAlbum;
    @Autowired
    private GoogleCloundService googleCloundService;
    @Autowired
    private ISong iSong;
    private final String BUCKET_NAME = "mosike_web";
    @GetMapping("/getAll")
    private String findAll(Model model, @RequestParam("page")Optional<Integer> page){
        int pageIndex = page.orElse(1);
        List<SongDTO> songDTOS =iSong.getAll();
        Page<AlbumDTO> albumDTOPage = iAlbum.findAll(PageRequest.of(pageIndex-1,5));
        model.addAttribute("total_page",albumDTOPage.getTotalPages());
        model.addAttribute("this_page",pageIndex);
        model.addAttribute("album_list",albumDTOPage.getContent());
        model.addAttribute("album",new AlbumModel());
        model.addAttribute("songs",songDTOS);
        return "admin/albums/album";
    }
    @PostMapping("create")
    private String create(@ModelAttribute AlbumModel albumModel, BindingResult bindingResult){
        System.out.println(albumModel);
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult);
            return "redirect:/admin/albums/getAll";
        }
        String fileUrl = googleCloundService.uploadFile(albumModel.getImgFile(), BUCKET_NAME,"album_img",albumModel.getImgFile().getOriginalFilename());
        albumModel.setImgAlbum(fileUrl);
        iAlbum.save(albumModel);
        return "redirect:/admin/albums/getAll";
    }
    @DeleteMapping("/deleteAlbum/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        int result = iAlbum.deleteById(id);
        if (result == 1) {
            return new ResponseEntity<>("ok", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("bad", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/searchSongs")
    public List<Song> searchSongs(Model model, @RequestParam("term")String searchTerm ){
        List<Song> songList = iSong.findByName(searchTerm);
        return songList;
    }


}
