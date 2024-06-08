package org.example.mosike_project.controller.admin;


import org.example.mosike_project.dto.CategoryDTO;
import org.example.mosike_project.services.ICategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/views")
public class AdminController {
    @Autowired
    private ICategory iCategory;
    @GetMapping("/getAll")
    public String index(Model model){
        List<CategoryDTO> list = iCategory.getAll();
        model.addAttribute("category_list",list);
        return "admin/Dashboard";
    }
}
