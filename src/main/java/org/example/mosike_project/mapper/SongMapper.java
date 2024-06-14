package org.example.mosike_project.mapper;


import org.example.mosike_project.dto.SongDTO;
import org.example.mosike_project.entities.Singer;
import org.example.mosike_project.entities.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SongMapper {
    @Autowired
    private AlbumMapper albumMapper;
    @Autowired
    private SingerMapper singerMapper;
    public SongDTO toDto(Song song){
        return SongDTO.builder()
                .id(song.getId())
                .nameSong(song.getNameSong())
                .imgSong(song.getImgSong())
                .linkSong(song.getLinkSong())
                .nameSinger(song.getSingerList().stream().reduce(new Singer(), (x, y) -> {
                    String allName = x.getNameSinger()+y.getNameSinger()+", ";
                    x.setNameSinger (allName);
                    return x;
                }).getNameSinger())
                .build();
    }
}
