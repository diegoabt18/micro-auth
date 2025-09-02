package com.crediya.model.users.gateways;

import com.crediya.model.users.Users;
import reactor.core.publisher.Mono;

public interface UsersRepository {
    Mono<Users> save(Users user);
    Mono<Boolean> existsByEmail(String email);
}
