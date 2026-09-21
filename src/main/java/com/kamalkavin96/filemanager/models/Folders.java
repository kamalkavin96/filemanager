package com.kamalkavin96.filemanager.models;

import java.time.LocalDateTime;

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
@Table(name = "folders", 
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {"name", "parent_id", "owner_id"}
        )
    }
)
@Data
@NoArgsConstructor 
@AllArgsConstructor 
public class Folders {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "parent_id", nullable = true)
    private String parentId;

    private String storagePath;

    private String folderPath;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
