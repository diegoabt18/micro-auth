package com.crediya.r2dbc;

import com.crediya.model.users.Users;
import com.crediya.model.users.gateways.UsersRepository;
import com.crediya.r2dbc.entity.UserEntity;
import com.crediya.r2dbc.helper.ReactiveAdapterOperations;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class R2dbcUserRepositoryAdapter extends ReactiveAdapterOperations<
        Users,
        UserEntity,
        Long,
        R2dbcUsersRepository
> implements UsersRepository {

    private final R2dbcUsersRepository r2dbcUsersRepository;

    public R2dbcUserRepositoryAdapter(R2dbcUsersRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Users.class));
        this.r2dbcUsersRepository = repository;
    }


    @Override
    public Mono<Boolean> existsByEmail(String email) {
       return repository.existsByEmail(email);
    }

}
