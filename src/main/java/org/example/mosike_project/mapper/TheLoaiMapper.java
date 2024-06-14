package org.example.mosike_project.mapper;

import org.example.mosike_project.dto.TheloaiDTO;
import org.example.mosike_project.entities.Theloai;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TheLoaiMapper {
    @Autowired
    private SongMapper songMapper;
    public TheloaiDTO toDto(Theloai theloai){
        return TheloaiDTO.builder()
                .nameTl(theloai.getNameTl())
                .songList(theloai.getSongList().stream().map(e->songMapper.toDto(e)).toList())
                .build();
    }
}
