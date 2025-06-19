package com.arion.savelinks.DTO;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateLinkDTO {
    @Pattern(regexp = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$", message = "Invalid URL format")

    private String link;
    @Size(max = 150, message = "Description can't exceed 150 characters")
    private String description;
    @Size(max = 100, message = "Title can't exceed 100 characters")
    private String link_title;
}
