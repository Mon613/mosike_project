package org.example.mosike_project.dto;

import lombok.*;
import org.example.mosike_project.entities.Singer;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SongDTO {
    private Long id;
    private String nameSong;
    private String imgSong;
    private String linkSong;
    private List<Singer> singer;



}
