package org.example.mosike_project.mapper;

import org.example.mosike_project.dto.PlaylistDTO;
import org.example.mosike_project.entities.Playlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlaylistMapper {
    @Autowired
    private SongMapper songMapper;
    public PlaylistDTO toDto(Playlist playlist){
        return PlaylistDTO.builder()
                .namePlaylist(playlist.getNamePlaylist())
                .imgPlaylist(playlist.getImgPlaylist())
                .songList(playlist.getSongList().stream().map(e->songMapper.toDto(e)).toList())
                .build();
    }
}
