package com.example.demo.dto;

import  com.example.demo.dto.Article;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Data
public class NewsDTO {
    private String status;
    private String totalResults;
    private List<Article> articles;
}
