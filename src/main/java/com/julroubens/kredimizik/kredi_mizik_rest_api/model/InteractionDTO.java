package com.julroubens.kredimizik.kredi_mizik_rest_api.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class InteractionDTO {

    private Long id;

    @Size(max = 255)
    private String action;

    private String commentContent;

    private LocalDateTime timestamp;

    private Boolean status;

    private Long song;

    @NotNull
    private Long user;

    private Long album;

    private Long target;

}
