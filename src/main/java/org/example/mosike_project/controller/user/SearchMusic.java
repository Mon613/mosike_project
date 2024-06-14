package org.example.mosike_project.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class SearchMusic {
    @GetMapping("/search")
    private String searchSongs(){
        return "user/search/search";
    }
}
