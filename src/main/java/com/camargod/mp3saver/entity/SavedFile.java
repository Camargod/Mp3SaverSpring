package com.camargod.mp3saver.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SavedFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    Long fileId;

    String name;
    String Url;

    @ManyToMany(mappedBy = "likedFiles")
    Set<UserUploader> downloadedBy;

    @ManyToOne()
    @JoinColumn(referencedColumnName = "user_id")
    UserUploader uploadedBy;
}
