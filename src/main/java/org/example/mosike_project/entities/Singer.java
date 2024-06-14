package org.example.mosike_project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tbl_singer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Singer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameSinger = "";
    private String imgSinger;
    @ManyToMany(mappedBy = "singerList")
    private List<Song> songList;

    @ManyToMany
    @JoinTable(
            name = "tbl_singer_album",
            joinColumns = @JoinColumn(name = "id_singer"),
            inverseJoinColumns = @JoinColumn(name = "id_album")
    )
    private List<Album> albumList;
}
