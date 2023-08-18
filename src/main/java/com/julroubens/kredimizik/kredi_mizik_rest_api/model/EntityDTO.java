package com.julroubens.kredimizik.kredi_mizik_rest_api.model;

import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EntityDTO {

    private Long id;

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String imageUrl;

    private Boolean status;

    private List<Long> instrument;

    private List<Long> type;

}
