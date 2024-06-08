package org.example.mosike_project.model;

import lombok.*;
import org.example.mosike_project.dto.SongDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaylistModel {
    private Long id;
    private String namePlaylist;
    private String imgPlaylist;
    private List<SongDTO> songDTOS;
    private MultipartFile imgFile;
}
