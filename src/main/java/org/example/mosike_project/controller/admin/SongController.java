package org.example.mosike_project.controller.admin;

import org.example.mosike_project.dto.SingerDTO;
import org.example.mosike_project.dto.SongDTO;
import org.example.mosike_project.entities.Song;
import org.example.mosike_project.model.UploadFile;
import org.example.mosike_project.model.SongModel;
import org.example.mosike_project.repositories.SongRepository;
import org.example.mosike_project.services.ISinger;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.google.common.io.Files.getFileExtension;

@Controller
@RequestMapping("admin/songs")
public class SongController {
    @Autowired
    private ISong iSong;
    @Autowired
    private GoogleCloundService googleCloundService;
    @Autowired
    private SongRepository songRepository;
    @Autowired
    private ISinger iSinger;
    private final String BUCKET_NAME = "mosike_web";

    @GetMapping("/getAll")
    private String findAll(Model model, @RequestParam("page") Optional<Integer> page) {
        int pageIndex = page.orElse(1);
        Page<SongDTO> songDTOPage = iSong.findAllPage(PageRequest.of(pageIndex - 1, 5));
        List<SingerDTO> singerDTOList = iSinger.getAll();
        model.addAttribute("total_page", songDTOPage.getTotalPages());
        model.addAttribute("this_page", pageIndex);
        model.addAttribute("song_list", songDTOPage.getContent());
        model.addAttribute("singers",singerDTOList);
//        model.addAttribute("song",new Song());
        model.addAttribute("files", new SongModel());

        return "admin/songs/song";
    }

    @PostMapping("create")
    public String createSong(@ModelAttribute SongModel songModel, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            return "redirect:/admin/songs/getAll";
        }
        System.out.println(songModel);
        // Upload các file ảnh vào folder "img"
        String fileUrl = googleCloundService.uploadFile(songModel.getImgFile(), BUCKET_NAME, "img_song", songModel.getImgFile().getOriginalFilename());
        // Upload các file mp3 vào folder "song"
        String songFileUrl = googleCloundService.uploadFile(songModel.getSongFile(), BUCKET_NAME, "songs", songModel.getSongFile().getOriginalFilename());
        songModel.setLinkSong(songFileUrl);
        songModel.setImgSong(fileUrl);
        iSong.save(songModel);
        return "redirect:/admin/songs/getAll";
    }

    @DeleteMapping("/deleteSong/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        int result = iSong.deleteById(id);
        if (result == 1) {
            return new ResponseEntity<>("ok", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("bad", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/update/{id}")
    public String update(Model model,@PathVariable("id")Long id){
        Song song = songRepository.findById(id).get();
        List<SingerDTO> singerDTOList = iSinger.getAll();
        model.addAttribute("singers",singerDTOList);
        model.addAttribute("song",song);
        return "admin/songs/edit";
    }
    @PostMapping("/update")
    public String update(Model model, @ModelAttribute("fileUpload") SongModel songModel){
        iSong.update(songModel);
        return "redirect:/admin/songs/getAll";
    }


}
