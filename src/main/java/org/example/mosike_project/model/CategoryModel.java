package org.example.mosike_project.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryModel {
    private long id;
    private String name;
    private int status;
}
