package com.brahmin.community.info.datafeeder;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


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
    @Column(name="occupation", length=50, nullable=false, unique=false)
    private String occupation;
    @Column(name="care_of", length=50, nullable=false, unique=false)
    private String careOf;
    @Column(name="relation", length=50, nullable=false, unique=false)
    private String relation;
    @Column(name="dob", length=50, nullable=false, unique=false)
    private String dob;
    @Column(name="registration_date", length=50, nullable=false, unique=false)
    private String registrationDate;
    @Column(name="gothram", length=50, nullable=false, unique=false)
    private String gothram;
    @Column(name="native_place", length=50, nullable=false, unique=false)
    private String nativePlace;
    @Column(name="set", length=50, nullable=false, unique=false)
    private String set;
    @Column(name="sub_set", length=50, nullable=false, unique=false)
    private String subSet;
    @Column(name="married", length=50, nullable=false, unique=false)
    private String married;

    // Getters and setters
}
