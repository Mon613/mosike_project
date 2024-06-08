package org.example.mosike_project.model;

import lombok.*;
import org.example.mosike_project.dto.SongDTO;
import org.example.mosike_project.entities.Song;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlbumModel {
    private Long id;
    private String nameAlbum;
    private String imgAlbum;
    private List<Song> songList;
    private MultipartFile imgFile;
}
