package com.ben.dto;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Video {

    private Long id;
    private String title;
    private String description;
    private String contentType;
    private String filePath;
}
