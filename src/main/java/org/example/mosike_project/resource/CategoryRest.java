package org.example.mosike_project.resource;


import org.example.mosike_project.dto.CategoryDTO;
import org.example.mosike_project.model.CategoryModel;
import org.example.mosike_project.services.ICategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryRest {
    @Autowired
    private ICategory category;
    @GetMapping("getCategory")
    private ResponseEntity<List<CategoryDTO>> getAll(){
        List<CategoryDTO> list = category.getAll();
        if (list.size()>0){
            return new ResponseEntity<>(list, HttpStatus.OK);
        }else return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);

    }
    @PostMapping("/new_cate")
    private ResponseEntity<String> save(@ModelAttribute CategoryModel categoryModel){
        int result = category.save(categoryModel);
        if (result == 1){
            return new ResponseEntity<>("ok",HttpStatus.OK);
        }else return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/deleteCate/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        int result = category.deleteById(id);
        if(result == 1){
            return new ResponseEntity<>("ok", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("bad",HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/update")
    public ResponseEntity<String> updateCate(@PathVariable("id")long id, @RequestBody CategoryModel categoryModel){
        category.update(categoryModel);
        return new ResponseEntity<>("okii",HttpStatus.OK);
    }

}
