package com.kamalkavin96.filemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kamalkavin96.filemanager.models.UserBaseFolder;

public interface UserBaseFolderRepo extends JpaRepository<UserBaseFolder, Long> {

    UserBaseFolder findByUserId(Long id);

}
