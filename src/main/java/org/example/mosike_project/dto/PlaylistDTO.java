package org.example.mosike_project.dto;

import lombok.*;
import org.example.mosike_project.entities.Song;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaylistDTO {
    private Long id;
    private String namePlaylist;
    private String imgPlaylist;
    private List<SongDTO> songList;
}
