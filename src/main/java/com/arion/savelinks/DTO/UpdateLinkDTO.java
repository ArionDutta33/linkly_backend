package com.arion.savelinks.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateLinkDTO {
    private String link;
    private String description;
    private String link_title;
}
