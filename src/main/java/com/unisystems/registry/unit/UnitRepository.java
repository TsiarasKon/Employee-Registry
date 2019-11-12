package com.unisystems.registry.unit;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends CrudRepository<Unit, Long> {
    @Override
    @RestResource(exported = false)
    <S extends Unit> S save(S entity);

    @Override
    @RestResource(exported = false)
    <S extends Unit> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    @RestResource(exported = false)
    void delete(Unit entity);

    @Override
    @RestResource(exported = false)
    void deleteById(Long aLong);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends Unit> entities);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
