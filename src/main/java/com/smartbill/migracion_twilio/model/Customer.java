package com.smartbill.migracion_twilio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//@Table(name = "tbl_customer", schema = "sistemas")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idCustomer;

    @Column(nullable = false, length = 70)
    private String firstName;

    @Column(nullable = false, length = 70)
    private String lastName;

    @Column(nullable = false, length = 20)
    private String documentType;

    @Column(nullable = false, length = 20, unique = true)
    private String documentNumber;

    @Column(nullable = false, length = 15)
    private String phoneNumber;

    @Column(length = 150)
    private String address;

    @Column(nullable = false, length = 80)
    private String email;

    @Column(nullable = false)
    private LocalDateTime dateOfBirth;

    @Column(nullable = false, length = 80)
    private String regime;
}