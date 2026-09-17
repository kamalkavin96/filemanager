package com.kamalkavin96.filemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kamalkavin96.filemanager.models.Files;

public interface FileRepo extends JpaRepository<Files, Long> {

}
