package org.example.mosike_project.services;


import org.example.mosike_project.dto.CategoryDTO;
import org.example.mosike_project.model.CategoryModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategory {
    List<CategoryDTO> getAll();
    int save(CategoryModel categoryModel);
    int deleteById(Long id);
    void update(CategoryModel categoryModel);
    Page<CategoryDTO> findAllPage(Pageable pageable);
}
