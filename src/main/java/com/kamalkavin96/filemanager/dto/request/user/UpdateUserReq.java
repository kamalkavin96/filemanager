package com.kamalkavin96.filemanager.dto.request.user;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor
public class UpdateUserReq {

    @NotNull (message = "User ID cannot be null or empty")
    private Long id;
    private String userName;
    @Email(message = "Invalid email formate")
    private String email;
    private LocalDate dob;
}
