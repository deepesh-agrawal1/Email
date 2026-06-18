package com.dev.Email.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Recipient {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        private String email;

}
