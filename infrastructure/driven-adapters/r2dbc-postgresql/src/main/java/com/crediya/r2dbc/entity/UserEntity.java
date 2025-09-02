package com.crediya.r2dbc.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Table(name = "users")
public class UserEntity {

    @Id
    private Long id;

    private String names;

    @Column("last_names")
    private String lastNames;

    @Column("birth_date")
    private LocalDateTime birthDate;

    private String address;

    private String phone;

    private String email;

    @Column("base_salary")
    private BigDecimal baseSalary;

}
