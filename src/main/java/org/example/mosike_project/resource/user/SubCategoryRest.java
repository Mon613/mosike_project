package org.example.mosike_project.resource.user;


import org.example.mosike_project.dto.SubCategoryDTO;
import org.example.mosike_project.entities.SubCategory;
import org.example.mosike_project.services.ICategory;
import org.example.mosike_project.services.ISubcategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("subCategory")
public class SubCategoryRest {
    @Autowired
    private ISubcategory iSubcategory;
    @Autowired
    private ICategory iCategory;

    @GetMapping("getAll")
    public ResponseEntity<List<SubCategoryDTO>> getAll(){
        List<SubCategoryDTO> list = iSubcategory.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PostMapping("/new_sub")
    public ResponseEntity<String> save(@ModelAttribute SubCategory subCategory){
        int result = iSubcategory.save(subCategory);
        return result>0? new ResponseEntity<>("ok",HttpStatus.OK):new ResponseEntity<>("loi",HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/deleteSub/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        int result = iSubcategory.deleteById(id);
        if(result == 1){
            return new ResponseEntity<>("ok", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("bad",HttpStatus.BAD_REQUEST);
        }
    }
}
