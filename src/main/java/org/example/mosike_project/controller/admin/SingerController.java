package org.example.mosike_project.controller.admin;

import org.example.mosike_project.dto.SingerDTO;
import org.example.mosike_project.dto.SongDTO;
import org.example.mosike_project.entities.Singer;
import org.example.mosike_project.model.SingerModel;
import org.example.mosike_project.model.SongModel;
import org.example.mosike_project.repositories.SingerRepository;
import org.example.mosike_project.services.ISinger;
import org.example.mosike_project.services.UploadService.GoogleCloundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("admin/singers")
public class SingerController {
    @Autowired
    private ISinger iSinger;
    @Autowired
    private GoogleCloundService googleCloundService;
    @Autowired
    private SingerRepository singerRepository;
    private final String BUCKET_NAME = "mosike_web";
    @GetMapping("/getAll")
    private String findAll(Model model, @RequestParam("page") Optional<Integer> page) {
        int pageIndex = page.orElse(1);
        Page<SingerDTO> singerDTOS = iSinger.findAllPage(PageRequest.of(pageIndex - 1, 5));
        model.addAttribute("total_page", singerDTOS.getTotalPages());
        model.addAttribute("this_page", pageIndex);
        model.addAttribute("singer_list", singerDTOS.getContent());
        model.addAttribute("singer",new SingerModel());
//        model.addAttribute("song",new Song());

        return "admin/singers/singer";
    }
    @PostMapping("create")
    public String createSong(@ModelAttribute SingerModel singerModel, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            return "redirect:/admin/singers/getAll";
        }
        System.out.println(singerModel);
        // Upload các file ảnh vào folder "img"
        String fileUrl = googleCloundService.uploadFile(singerModel.getFileImg(), BUCKET_NAME, "img_singer", singerModel.getFileImg().getOriginalFilename());
        singerModel.setImgSinger(fileUrl);
        iSinger.save(singerModel);
        return "redirect:/admin/singers/getAll";
    }
}
