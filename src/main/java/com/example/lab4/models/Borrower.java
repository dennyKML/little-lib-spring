package com.example.lab4.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
public class Borrower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;

    @OneToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book borrowedBook;
}
