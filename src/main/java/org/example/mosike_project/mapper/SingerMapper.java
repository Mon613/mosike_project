package org.example.mosike_project.mapper;

import org.example.mosike_project.dto.SingerDTO;
import org.example.mosike_project.entities.Singer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SingerMapper {
    @Autowired
    private SongMapper songMapper;
    @Autowired
    private AlbumMapper albumMapper;
    public SingerDTO toDto(Singer singer){
        return SingerDTO.builder()
                .id(singer.getId())
                .nameSinger(singer.getNameSinger())
                .songList(singer.getSongList().stream().map(e->songMapper.toDto(e)).toList())
                .imgSinger(singer.getImgSinger())
                .albums(singer.getAlbumList().stream().map(e->albumMapper.toDto(e)).toList())
                .build();
    }
}
