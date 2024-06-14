package org.example.mosike_project.mapper;

import org.example.mosike_project.dto.AlbumDTO;
import org.example.mosike_project.entities.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlbumMapper {
    @Autowired
    private SongMapper songMapper;
    public AlbumDTO toDto(Album album){
        return AlbumDTO.builder()
                .id(album.getId())
                .nameAlbum(album.getNameAlbum())
                .imgAlbum(album.getImgAlbum())
                .songList(album.getSongList().stream().map(e->songMapper.toDto(e)).toList())
                .build();
    }
}
