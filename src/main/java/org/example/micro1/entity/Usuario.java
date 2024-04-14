package org.example.micro1.entity;

import jakarta.persistence.*;
import jdk.jfr.SettingDefinition;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    @Column(name = "username" , unique = true ,length = 50, nullable = false)
    private String username;
    @Column(name="password", length = 100, nullable = false)
    private String password;
    @Column(name="email", unique = true, length = 100, nullable = false)
    private String email;
    @Column(name="role", length = 20, nullable = false)
    private String role;
}
