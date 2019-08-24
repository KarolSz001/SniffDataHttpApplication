package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class DictionaryPhrase {
    private String definition;
    private String permalink;
    private Integer thumbs_up;
    private String[] sound_urls;
    private String author;
    private String word;
    private String defid;
    private String current_vote;
    private String written_on;
    private String example;
    private Integer thumbs_down;
}
