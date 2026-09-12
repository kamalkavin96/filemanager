package com.kamalkavin96.filemanager.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailDao {
    private Long id;
    private String username;
    private String email;
    private LocalDate dob;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private String roles;
}
