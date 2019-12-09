package com.unisystems.registry.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginUserRepository extends CrudRepository<LoginUser, Long> {

    @Override
    @RestResource(exported = false)
    <S extends LoginUser> S save(S entity);

    @Override
    @RestResource(exported = false)
    <S extends LoginUser> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    @RestResource(exported = false)
    void delete(LoginUser entity);

    @Override
    @RestResource(exported = false)
    void deleteById(Long aLong);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends LoginUser> entities);

    @Override
    @RestResource(exported = false)
    void deleteAll();

}
