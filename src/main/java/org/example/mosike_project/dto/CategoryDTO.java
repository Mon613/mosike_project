package org.example.mosike_project.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO {
    private long id;
    private String name;
    private int status;
    private List<SubCategoryDTO> subCategoryDTOS;

    public CategoryDTO(String name) {
        this.name=name;
    }
}
