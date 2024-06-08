package org.example.mosike_project.model;

import lombok.*;
import org.example.mosike_project.dto.SongDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TheLoaiModel {
    private Long id;
    private String nameTl;
    private List<SongDTO> songDTOS;
}
