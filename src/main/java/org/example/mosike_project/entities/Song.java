package org.example.mosike_project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Entity
@Table(name="tbl_song")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameSong;
    private String imgSong;
    private String linkSong;
    @ManyToMany
    @JoinTable(
            name = "tbl_song_singer",
            joinColumns = @JoinColumn(name = "songId"),
            inverseJoinColumns = @JoinColumn(name = "singerId")
    )
    private List<Singer> singerList;
    @ManyToMany(mappedBy = "songList")
    private List<Album> albumList;
    @ManyToMany(mappedBy = "songList")
    private List<Playlist> playlists;
    @ManyToMany(mappedBy = "songList")
    private List<Theloai> theloais;
}
