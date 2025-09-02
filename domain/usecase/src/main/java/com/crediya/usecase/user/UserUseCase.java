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
        System.out.println("llego al metodo useCase ");

        return userRepository.existsByEmail(user.getEmail())
                .flatMap(exists -> {
                    if (exists) {
                        System.out.println("entra al if de existe");
                        return Mono.error(new UserInvalidException("El correo ya está registrado: " + user.getEmail()));
                    }
                    System.out.println("paso la validacion de correo");
                    System.out.println(user.getBirthDate());
                    return userRepository.save(user);
                });
    }


}
