package com.julroubens.kredimizik.kredi_mizik_rest_api.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LyricDTO {

    private Long id;

    @Size(max = 255)
    private String title;

    private String lyric;

    @Size(max = 255)
    private String imageUrl;

    private Boolean status;

    private Long language;

    private Long song;

}
