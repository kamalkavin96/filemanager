package com.kamalkavin96.filemanager.dto.response;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRes {

    private Long id;
    private String username;
    private Set<String> role;
    private  String email;

}
