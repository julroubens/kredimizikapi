package com.julroubens.kredimizik.kredi_mizik_rest_api.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FieldError {

    private String field;
    private String errorCode;

}
