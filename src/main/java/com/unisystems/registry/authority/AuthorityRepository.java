package com.unisystems.registry.authority;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long> {

    @Override
    @RestResource(exported = false)
    <S extends Authority> S save(S entity);

    @Override
    @RestResource(exported = false)
    <S extends Authority> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    @RestResource(exported = false)
    void delete(Authority entity);

    @Override
    @RestResource(exported = false)
    void deleteById(Long aLong);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends Authority> entities);

    @Override
    @RestResource(exported = false)
    void deleteAll();

}
