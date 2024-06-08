package org.example.mosike_project.mapper;



import org.example.mosike_project.dto.CategoryDTO;
import org.example.mosike_project.dto.SubCategoryDTO;
import org.example.mosike_project.entities.SubCategory;
import org.springframework.stereotype.Component;

@Component
public class SubCategoryMapper {
    public SubCategoryDTO toDto(SubCategory subCategory){
        return SubCategoryDTO.builder()
                .id(subCategory.getId())
                .name(subCategory.getName())
                .categoryDTO(new CategoryDTO(
                        subCategory.getCategory().getName()
                ))
                .build();
    }
}
