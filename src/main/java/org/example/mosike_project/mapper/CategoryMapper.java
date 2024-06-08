package org.example.mosike_project.mapper;


import org.example.mosike_project.dto.CategoryDTO;
import org.example.mosike_project.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    @Autowired
    private SubCategoryMapper subCategoryMapper;
    public CategoryDTO toDto(Category category){
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .status(category.getStatus())
                .subCategoryDTOS(category.getSubCategories().stream().map(e->subCategoryMapper.toDto(e)).toList())
                .build();
    }
}
