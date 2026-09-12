package com.kamalkavin96.filemanager.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kamalkavin96.filemanager.utils.JsonToListDeserializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailRes {

    private Long id;
    private String username;
    private String email;
    private LocalDate dob;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    @JsonDeserialize(using = JsonToListDeserializer.class)
    private List<String> roles;

}
