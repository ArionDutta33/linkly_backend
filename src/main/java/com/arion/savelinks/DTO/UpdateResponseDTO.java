package com.arion.savelinks.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateResponseDTO {
    private Long id;
    private String link_title;
    private String link;
    private String description;
}
