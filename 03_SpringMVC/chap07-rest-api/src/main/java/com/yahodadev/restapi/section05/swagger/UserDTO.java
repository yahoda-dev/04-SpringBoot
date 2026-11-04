package com.yahodadev.restapi.section05.swagger;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int no;
    private String id, pwd, name;
    private Date enrollAt;

    public UserDTO(String id, String pwd, String name) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
    }
}


