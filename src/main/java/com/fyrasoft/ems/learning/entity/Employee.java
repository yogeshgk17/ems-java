package com.fyrasoft.ems.learning.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    private String department;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="address_id")
    private Address address;

}
