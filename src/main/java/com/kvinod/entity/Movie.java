package com.kvinod.entity;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Movie implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String title;
    private List<String> director;
    private String   released;
    private Imdb imdb;
    private LocalDateTime createDate;

}
