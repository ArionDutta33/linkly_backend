package com.arion.savelinks.DTO;

import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
public class RegisterRequest {
    @NotBlank(message = "Username is required")
    @Size(min = 4, max = 30, message = "Username must be between 4 and 30 characters")

    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 8,message = "Minimum length of password must be 8 characters")
    private String password;

    @Email
    @NotBlank(message = "Email is required")
    private String email;
}
