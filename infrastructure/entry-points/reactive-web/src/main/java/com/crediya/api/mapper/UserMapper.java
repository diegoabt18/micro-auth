package com.crediya.api.mapper;

import com.crediya.api.dto.UserRequestDTO;
import com.crediya.api.exeptions.InvalidFormatDate;
import com.crediya.model.users.Users;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserMapper {

    public static Users toDomain(UserRequestDTO dto) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate  localDate;

        try {
            localDate = LocalDate.parse(dto.getFechaNacimiento(), formatter);
        } catch (Exception e) {
            throw new InvalidFormatDate(e.getMessage());
        }

        LocalDateTime birthDate = localDate.atStartOfDay();

        return Users.builder()
                .names(dto.getNombres())
                .lastNames(dto.getApellidos())
                .birthDate(birthDate)
                .address(dto.getDireccion())
                .phone(dto.getTelefono())
                .email(dto.getCorreoElectronico())
                .baseSalary(dto.getSalarioBase())
                .build();
    }

}
