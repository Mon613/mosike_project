package org.example.mosike_project.mapper;

import org.example.mosike_project.dto.SingerDTO;
import org.example.mosike_project.entities.Singer;
import org.springframework.stereotype.Component;

@Component
public class SingerMapper {
    private SongMapper songMapper;
    private AlbumMapper albumMapper;
    public SingerDTO toDto(Singer singer){
        return SingerDTO.builder()
                .id(singer.getId())
                .nameSinger(singer.getNameSinger())
                .songList(singer.getSongList())
                .imgSinger(singer.getImgSinger())
                .albums(singer.getAlbumList())
                .build();
    }
}
