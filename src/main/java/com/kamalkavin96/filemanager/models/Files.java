package com.kamalkavin96.filemanager.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "files", uniqueConstraints = {
    @UniqueConstraint(
        columnNames = {"name", "folder_id", "owner_id"}
    )
})
@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class Files {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String storagePath;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "folder_id")
    private String folderId;

}
