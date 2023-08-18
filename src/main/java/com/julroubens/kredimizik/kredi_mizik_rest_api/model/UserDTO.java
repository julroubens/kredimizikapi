package com.julroubens.kredimizik.kredi_mizik_rest_api.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO {

    private Long id;

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String password;

    @Size(max = 255)
    private String email;

    @Size(max = 255)
    private String username;

    @Size(max = 255)
    private String imageUrl;

    private Boolean status;

}
