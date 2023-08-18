package com.julroubens.kredimizik.kredi_mizik_rest_api.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LanguageDTO {

    private Long id;

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String trigram;

    private Boolean status;

}
