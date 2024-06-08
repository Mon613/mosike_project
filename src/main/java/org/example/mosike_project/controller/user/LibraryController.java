package org.example.mosike_project.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user/libraries")
public class LibraryController {
    @GetMapping("library")
    private String getLibrary(){
        return "user/library/library";
    }
}
