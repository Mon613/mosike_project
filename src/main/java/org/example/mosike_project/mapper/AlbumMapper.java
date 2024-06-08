package org.example.mosike_project.mapper;

import org.example.mosike_project.dto.AlbumDTO;
import org.example.mosike_project.entities.Album;
import org.springframework.stereotype.Component;

@Component
public class AlbumMapper {
    private SongMapper songMapper;
    public AlbumDTO toDto(Album album){
        return AlbumDTO.builder()
                .id(album.getId())
                .nameAlbum(album.getNameAlbum())
                .imgAlbum(album.getImgAlbum())
                .songList(album.getSongList())
                .build();
    }
}
