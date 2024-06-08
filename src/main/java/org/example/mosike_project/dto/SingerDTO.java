package org.example.mosike_project.dto;

import lombok.*;
import org.example.mosike_project.entities.Album;
import org.example.mosike_project.entities.Song;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SingerDTO {
    private Long id;
    private String nameSinger;
    private String imgSinger;
    private List<Song> songList;
    private List<Album> albums;
}
