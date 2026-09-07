package com.kamalkavin96.filemanager.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUserRes {

    private Long id;
    private String username;
    private String role;

}
