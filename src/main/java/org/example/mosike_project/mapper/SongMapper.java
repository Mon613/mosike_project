package org.example.mosike_project.mapper;


import org.example.mosike_project.dto.SongDTO;
import org.example.mosike_project.entities.Song;
import org.springframework.stereotype.Component;

@Component
public class SongMapper {
    private AlbumMapper albumMapper;
    public SongDTO toDto(Song song){
        return SongDTO.builder()
                .id(song.getId())
                .nameSong(song.getNameSong())
                .imgSong(song.getImgSong())
                .linkSong(song.getLinkSong())
                .singer(song.getSingerList())
                .build();
    }
}
