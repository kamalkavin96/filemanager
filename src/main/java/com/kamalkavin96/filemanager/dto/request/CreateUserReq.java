package com.kamalkavin96.filemanager.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUserReq {

    private String username;
    private String password;
    private String role;


}
