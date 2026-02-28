package com.jspider.security.BasicAuthInDataBase.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name is Required")
    private String name;

    private String dept;

    private String mobile;

    @NotBlank(message = "Username is Required")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "Password is Required")
    private String password;

    @NotBlank(message = "Role is Required")
    private String role;


}
