package com.arion.savelinks.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkDTO {
    private Long id;
    @NotBlank(message = "Link title is required")
    @Size(min = 3, max = 100, message = "Link title must be between 3 and 100 characters")
    private String link_title;
    @NotBlank(message = "Link is required")
    @Pattern(regexp = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$", message = "Invalid URL format")

    private String link;
    @Size(max = 150, message = "Description can't exceed 150 characters")
    private String description;


}
