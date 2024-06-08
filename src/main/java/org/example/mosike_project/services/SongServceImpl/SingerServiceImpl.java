package org.example.mosike_project.services.SongServceImpl;

import org.example.mosike_project.dto.SingerDTO;
import org.example.mosike_project.entities.Singer;
import org.example.mosike_project.mapper.SingerMapper;
import org.example.mosike_project.model.SingerModel;
import org.example.mosike_project.repositories.SingerRepository;
import org.example.mosike_project.services.ISinger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SingerServiceImpl implements ISinger {
    @Autowired
    private SingerRepository singerRepository;
    @Autowired
    private SingerMapper singerMapper;
    @Override
    public List<SingerDTO> getAll() {
        List<SingerDTO> list = singerRepository.findAll().stream().map(e->singerMapper.toDto(e)).toList();
        return list;
    }

    @Override
    public int save(SingerModel singerModel) {
        try {
            Singer singer = new Singer();
            singer.setNameSinger(singerModel.getNameSinger());
            singer.setImgSinger(singerModel.getImgSinger());
//            singer.setAlbumList(singerModel.getAlbums());
            singerRepository.save(singer);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public List<Singer> findByNameSinger(String txt) {
        List<Singer> singerList = singerRepository.findByNameSinger(txt);
        return singerList;
    }

    @Override
    public void update(SingerModel singerModel) {
        Singer singer = singerRepository.findById(singerModel.getId()).get();

    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }

    @Override
    public Page<SingerDTO> findAllPage(Pageable pageable) {
        Page<SingerDTO> page = singerRepository.findAll(pageable).map(e->singerMapper.toDto(e));
        return page;
    }
}
