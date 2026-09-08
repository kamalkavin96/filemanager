package com.kamalkavin96.filemanager.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class DeleteRoleReq {

    @NotNull (message = "Role ID cannot be null")
    @Min (value = 1, message = "Role ID must be a valid positive number")
    private Long id;

}
