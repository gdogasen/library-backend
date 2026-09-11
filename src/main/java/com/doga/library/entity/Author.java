package com.doga.library.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Setter;
import lombok.Getter;
import jakarta.validation.constraints.NotBlank;


@Getter
@Setter
@Entity

public class Author {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)

    private Long id;

    @NotBlank(message = "First name cannot be empty")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    private String lastName;

}


