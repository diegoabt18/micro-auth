package com.crediya.api;

import com.crediya.api.dto.UserRequestDTO;
import com.crediya.api.mapper.UserMapper;
import com.crediya.usecase.user.UserUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class Handler {

    private final UserUseCase userUseCase;

   // @Transactional
    public Mono<ServerResponse> createUser(ServerRequest serverRequest) {

        return serverRequest.bodyToMono(UserRequestDTO.class)
                .map(UserMapper::toDomain)
                .flatMap(userUseCase::registerUser) // devuelve Mono<Users>
                .flatMap(savedUser -> ServerResponse.ok().bodyValue(savedUser))
                .switchIfEmpty(ServerResponse.badRequest().bodyValue("No se pudo crear el usuario")) // evita el null
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue(e.getMessage()));
    }
}
