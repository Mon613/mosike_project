package org.example.mosike_project.model;

import lombok.*;
import org.example.mosike_project.dto.CategoryDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubCategoryModel {
    private long id;
    private String name;
    private CategoryDTO categoryDTO;
}
