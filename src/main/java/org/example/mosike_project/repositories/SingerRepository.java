package org.example.mosike_project.repositories;

import org.example.mosike_project.entities.Singer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SingerRepository extends JpaRepository<Singer,Long> {
    List<Singer> findByNameSinger(String txt);
}
