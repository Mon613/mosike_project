package org.example.mosike_project.dto;

import lombok.*;
import org.example.mosike_project.entities.Song;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TheloaiDTO {
    private Long id;
    private String nameTl;
    private List<Song> songList;
}
