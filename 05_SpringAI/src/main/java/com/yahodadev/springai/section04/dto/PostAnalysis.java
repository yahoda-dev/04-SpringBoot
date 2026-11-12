package com.yahodadev.springai.section04.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostAnalysis {

    private String summary;
    private String category;
    private List<String> tags;
}
