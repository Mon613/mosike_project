package org.example.mosike_project.services.SongServceImpl;

import org.example.mosike_project.dto.AlbumDTO;
import org.example.mosike_project.entities.Album;
import org.example.mosike_project.mapper.AlbumMapper;
import org.example.mosike_project.mapper.SongMapper;
import org.example.mosike_project.model.AlbumModel;
import org.example.mosike_project.repositories.AlbumRepository;
import org.example.mosike_project.services.IAlbum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AlbumServiceImpl implements IAlbum {
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private AlbumMapper albumMapper;
    @Autowired
    private SongMapper songMapper;
    @Override
    public Page<AlbumDTO> findAll(Pageable pageable) {
        Page<AlbumDTO> albumDTOPage = albumRepository.findAll(pageable).map(e->albumMapper.toDto(e));
        return albumDTOPage;
    }

    @Override
    public int save(AlbumModel albumModel) {
        try {
            Album album = new Album();
            album.setNameAlbum(albumModel.getNameAlbum());
            album.setImgAlbum(albumModel.getImgAlbum());
            album.setSongList(albumModel.getSongList());
            albumRepository.save(album);
            return 1;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public List<Album> findByName(String txt) {
        List<Album> list = albumRepository.findByNameAlbum(txt);
        return list;
    }

    @Override
    public void update(AlbumDTO albumDTO) {

    }

    @Override
    public int deleteById(Long id) {
       try {
           albumRepository.deleteById(id);
           return 1;
       }catch (Exception e){
           System.out.println(e.getMessage());
       }
       return 0;
    }

    @Override
    public List<AlbumDTO> getAll() {
        List<AlbumDTO> list = albumRepository.findAll().stream().map(e->albumMapper.toDto(e)).toList();
        return list;
    }
}
