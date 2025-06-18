package com.arion.savelinks.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = false)
public class LoginRequest {
    private String username;
    private String password;
}
