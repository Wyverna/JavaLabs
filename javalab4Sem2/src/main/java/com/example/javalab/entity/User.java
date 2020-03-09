package com.example.javalab.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "UsersJavaLab4")
public class User {
    @Id
    @Column(name = "Login")
    private String login;
    @Column(name = "Password")
    private String password;
    @Column(name = "Role")
    private String role;
}
