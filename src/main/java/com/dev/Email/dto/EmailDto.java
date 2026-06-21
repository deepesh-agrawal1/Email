package com.dev.Email.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class EmailDto {


        private List<String> recipients;
        private String subject;
        private String body;



}
