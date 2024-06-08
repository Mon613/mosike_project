package org.example.mosike_project.mapper;

import org.example.mosike_project.dto.PlaylistDTO;
import org.example.mosike_project.entities.Playlist;
import org.springframework.stereotype.Component;

@Component
public class PlaylistMapper {
    private SongMapper songMapper;
    public PlaylistDTO toDto(Playlist playlist){
        return PlaylistDTO.builder()
                .namePlaylist(playlist.getNamePlaylist())
                .imgPlaylist(playlist.getImgPlaylist())
                .songList(playlist.getSongList())
                .build();
    }
}
