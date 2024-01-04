package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@JsonPropertyOrder({ "id", "firstName", "lastName", "age", "gender", "contactNumber", "email", "address", "registrationDate", "isActive" })
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String contactNumber;
    private String email;
    private String address;
    private Date registrationDate; // Date when the patient registered
    private boolean isActive; // Whether the patient is currently active or not

    // Getters and setters, constructors, etc.

    // Additional methods or validations can be added as needed
}

