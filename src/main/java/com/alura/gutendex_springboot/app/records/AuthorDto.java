package com.alura.gutendex_springboot.app.records;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthorDto(String name,
                        @JsonAlias("birth_year") int birthYear,
                        @JsonAlias("death_year") int deathYear) {
}
