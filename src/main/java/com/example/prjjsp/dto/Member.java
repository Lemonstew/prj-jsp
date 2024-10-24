package com.example.prjjsp.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Member {
    private String id;
    private String nickName;
    private String password;
    private String description;
    private LocalDate inserted;
}
