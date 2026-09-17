package com.kamalkavin96.filemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kamalkavin96.filemanager.models.Folders;

public interface FolderRepo extends JpaRepository<Folders, Long> {

}
