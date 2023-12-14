package com.brahmin.community.info.datafeeder;

import jakarta.persistence.*;
import lombok.Data;



@Data
@Entity
@Table(name="registered-details")
public class Registrant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name", length=50, nullable=false, unique=false)
    private String firstName;
    @Column(name="last_name", length=50, nullable=false, unique=false)
    private String lastName;
    @Column(name="phone_number", length=10, nullable=false, unique=false)
    private long phoneNumber;
    @Column(name="address", length=100, nullable=false, unique=false)
    private String address;
    @Column(name="designation", length=50, nullable=false, unique=false)
    private String designation;

    // Getters and setters
}
