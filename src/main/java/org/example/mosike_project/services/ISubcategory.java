package org.example.mosike_project.services;


import org.example.mosike_project.dto.SubCategoryDTO;
import org.example.mosike_project.entities.SubCategory;
import org.example.mosike_project.model.SubCategoryModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISubcategory {
    int save(SubCategory subCategory);
    List<SubCategoryDTO> getAll();
    void update(SubCategoryModel subCategoryModel);
    int deleteById(long id);
    Page<SubCategoryDTO> findAllPage(Pageable pageable);
}
