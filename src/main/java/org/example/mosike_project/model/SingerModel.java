package org.example.mosike_project.model;

import lombok.*;
import org.example.mosike_project.dto.AlbumDTO;
import org.example.mosike_project.dto.SongDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SingerModel {
    private Long id;
    private String nameSinger;
    private String imgSinger;
//    private List<SongDTO> songDTOS;
//    private List<AlbumDTO> albumDTOS;
    private MultipartFile fileImg;
}
