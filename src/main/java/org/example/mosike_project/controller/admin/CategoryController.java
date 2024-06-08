package org.example.mosike_project.controller.admin;


import org.example.mosike_project.dto.CategoryDTO;
import org.example.mosike_project.entities.Category;
import org.example.mosike_project.model.CategoryModel;
import org.example.mosike_project.services.ICategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/categories")
public class CategoryController {
    @Autowired
    private ICategory iCategory;
    @GetMapping("/getAll")
    public String findAllPage(Model model, @RequestParam("page")Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        int pageIndex = page.orElse(1);
        int sizeIndex = size.orElse(5);
        Page<CategoryDTO> categoryDTOPage = iCategory.findAllPage(PageRequest.of(pageIndex-1,sizeIndex));
        model.addAttribute("total_page",categoryDTOPage.getTotalPages());
        model.addAttribute("this_page",pageIndex);
        model.addAttribute("category_list",categoryDTOPage.getContent());
        return "admin/categories/Category";
    }
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("category",new Category());
        return "admin/categories/create";
    }
    @PostMapping("create")
    public String add(@ModelAttribute CategoryModel categoryModel){
        iCategory.save(categoryModel);
        return "redirect:/admin/categories/getAll";
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        int result = iCategory.deleteById(id);
        if(result == 1){
            return new ResponseEntity<>("ok", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("bad",HttpStatus.BAD_REQUEST);
        }
    }

}
