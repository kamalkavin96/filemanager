package com.kamalkavin96.filemanager.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class CreateRoleReq {

    @NotEmpty(message = "Role should not be empty")
    private String name;
}
