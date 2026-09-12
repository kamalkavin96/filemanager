package com.kamalkavin96.filemanager.dto.request.user;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUserReq {

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 5, max = 50, message = "Name must be 5 to 50 characters")
    private String username;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email formate")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 5, max = 50, message = "Password must be 5 to 50 characters")
    private String password;
    
    private LocalDate dob;

}
