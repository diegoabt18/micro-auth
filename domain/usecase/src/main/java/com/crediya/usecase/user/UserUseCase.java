package com.crediya.usecase.user;

import com.crediya.model.users.Users;
import com.crediya.model.users.exeptions.UserInvalidException;
import com.crediya.model.users.gateways.UsersRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UserUseCase {

    private final UsersRepository userRepository;


    public Mono<Users> registerUser(Users user) {
        System.out.println("Creacion de usuarios");
        return userRepository.existsByEmail(user.getEmail())
                .flatMap(exists -> {
                    if (exists) {
                        System.out.println("Correo ya ha sido registrado");
                        return Mono.error(new UserInvalidException("El correo ya está registrado: " + user.getEmail()));
                    }
                    System.out.println("Guardando usuario en bases de datos");
                    System.out.println(user.getBirthDate());
                    return userRepository.save(user);
                });
    }

    public Mono<Boolean> existUser(Long id) {
        System.out.println("Consultado si existe el usuario: " + id);
        return userRepository.existsByUserId(id)
                .doOnNext(exists -> {
                    if (exists) {
                        System.out.println("El usuario con id " + id + " ya existe");
                    } else {
                        System.out.println("El usuario con id " + id + " no existe");
                    }
                });
    }


}
