package com.crediya.model.users;
import com.crediya.model.users.exeptions.UserInvalidException;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Users {

    private Long id;
    private String names;
    private String lastNames;
    private LocalDateTime birthDate;
    private String address;
    private String phone;
    private String email;
    private BigInteger baseSalary;

    public Users(String names, String lastNames, LocalDateTime birthDate, String address, String phone, String email, BigInteger baseSalary) {
        this.setNames(names);
        this.setLastNames(lastNames);
        this.setEmail(email);
        this.birthDate = birthDate;
        this.address = address;
        this.phone = phone;
        this.setBaseSalary(baseSalary);
    }

    public void setNames(String names) {

        if (names == null || names.isBlank()) {
            throw new UserInvalidException("El nombre es obligatorio");
        }

        this.names = names;
    }

    public void setLastNames(String lastNames) {

        if (lastNames == null || lastNames.isBlank()) {
            throw new UserInvalidException("El nombre es obligatorio");
        }

        this.lastNames = lastNames;
    }


    public void setEmail(String email) {

        if (email == null || email.isBlank()) {
            throw new UserInvalidException("El nombre es obligatorio");
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z]+\\.com$")) {
            throw new UserInvalidException("El correo electrónico no tiene un formato válido");
        }

        this.email = email;
    }

    public void setBaseSalary(BigInteger baseSalary) {

        if (baseSalary == null) {
            throw new UserInvalidException("El nombre es obligatorio");
        }

        if (baseSalary.compareTo(BigInteger.ZERO) < 0) {
            throw new UserInvalidException("El salario base no puede ser negativo");
        }
        if (baseSalary.compareTo(new BigInteger("15000000")) > 0) {
            throw new UserInvalidException("El salario base no puede ser mayor a 15,000,000");
        }

        this.baseSalary = baseSalary;
    }


}
