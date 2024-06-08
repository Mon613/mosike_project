package org.example.mosike_project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="tbl_theloai")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Theloai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameTl;
    @ManyToMany
    @JoinTable(
            name = "tbl_theloai_song",
            joinColumns = @JoinColumn(name = "id_theloai"),
            inverseJoinColumns = @JoinColumn(name = "id_song")
    )

    private List<Song> songList;

}
