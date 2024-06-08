package org.example.mosike_project.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubCategoryDTO {
    private Long id;
    private String name;
    private CategoryDTO categoryDTO;
}
