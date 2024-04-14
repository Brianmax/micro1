package org.example.micro1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="tbl_customers")
public class PersonaEntity {
    @Id
    @Column(name = "num_document" , unique = true ,length = 8, nullable = false)
    private String numDocument;
    @Column(name="first_name", length = 100, nullable = false)
    private String name;
    @Column(name="first_surname", length = 100, nullable = false)
    private String firstSurname;
    @Column(name="second_surname", length = 100, nullable = false)
    private String secondSurname;
    @Column(unique=true, nullable=false)
    private String email;
    @Column(name = "telephone",length = 15, nullable = false)
    private String telephone;
    @Column(name = "status", nullable = false)
    private Integer status;
}
