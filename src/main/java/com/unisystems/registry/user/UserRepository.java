package com.unisystems.registry.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    @RestResource(exported = false)
    <S extends User> S save(S entity);

    @Override
    @RestResource(exported = false)
    <S extends User> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    @RestResource(exported = false)
    void delete(User entity);

    @Override
    @RestResource(exported = false)
    void deleteById(Long aLong);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends User> entities);

    @Override
    @RestResource(exported = false)
    void deleteAll();

}
