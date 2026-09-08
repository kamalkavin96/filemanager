package com.kamalkavin96.filemanager.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleRes {

    private Long id;
    private String username;
    private String email;
    private List<String> roles;

}
