package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class Actor {

    private Integer id;
    private String url;
    private String name;
    private Country country;
    private String birthday;
    private String deathday;
    private String gender;
    private Image image;
    private Link links;


}
