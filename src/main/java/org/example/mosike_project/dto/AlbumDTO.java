package org.example.mosike_project.dto;

import lombok.*;
import org.example.mosike_project.entities.Song;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlbumDTO {
    private Long id;
    private String nameAlbum;
    private String imgAlbum;
    private List<Song> songList;

    public AlbumDTO(String nameAlbum){
        this.nameAlbum=nameAlbum;
    }
}
