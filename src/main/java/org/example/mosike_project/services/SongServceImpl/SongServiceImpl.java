package org.example.mosike_project.services.SongServceImpl;


import org.example.mosike_project.dto.SongDTO;
import org.example.mosike_project.entities.Song;
import org.example.mosike_project.mapper.SongMapper;
import org.example.mosike_project.model.SongModel;
import org.example.mosike_project.repositories.SongRepository;
import org.example.mosike_project.services.ISong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements ISong {
    @Autowired
    private SongRepository songRepository;
    @Autowired
    private SongMapper songMapper;

    @Override
    public List<SongDTO> getAll() {
        List<SongDTO> list = songRepository.findAll().stream().map(e->songMapper.toDto(e)).toList();
        return list;
    }

    @Override
    public int save(SongModel songModel) {
        try {
            Song song = new Song();
            song.setNameSong(songModel.getNameSong());
            song.setImgSong(songModel.getImgSong());
            song.setLinkSong(songModel.getLinkSong());
            song.setSingerList(songModel.getSingerList());
            songRepository.save(song);
            return 1;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public List<Song> findByName(String txt) {
        List<Song> songs = songRepository.findByNameSong(txt);
        return songs;
    }

    @Override
    public void update(SongModel songModel) {
        Song song = songRepository.findById(songModel.getId()).get();
        song.setNameSong(songModel.getNameSong());
        song.setSingerList(songModel.getSingerList());
        songRepository.save(song);

    }

    @Override
    public int deleteById(Long id) {
        Song song = songRepository.findById(id).get();
        try {
            if (song!=null){
                song.getAlbumList().clear();
                song.getPlaylists().clear();
                song.getSingerList().clear();
                song.getTheloais().clear();
                songRepository.save(song);
            }
            songRepository.deleteById(id);
            return 1;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public Page<SongDTO> findAllPage(Pageable pageable) {
        Page<SongDTO> songDTOPage = songRepository.findAll(pageable).map(e->songMapper.toDto(e));
        return songDTOPage;
    }

    @Override
    public Song findById(Long id) {
        Song song = songRepository.findById(id).get();
        return song;
    }

}
