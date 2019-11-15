package com.unisystems.registry.unit;

import com.unisystems.registry.GenericError;
import com.unisystems.registry.GenericResponse;
import com.unisystems.registry.InvalidIdException;
import com.unisystems.registry.department.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UnitService {
    private UnitMapper mapper;
    private UnitRepository repository;
    private DepartmentRepository departmentRepository;

    public UnitService(UnitMapper mapper, UnitRepository repository, DepartmentRepository departmentRepository) {
        this.mapper = mapper;
        this.repository = repository;
        this.departmentRepository = departmentRepository;
    }

    public GenericResponse<MultipleUnitResponse> getAllUnits() {
        Iterable<Unit> retrievedUnits = repository.findAll();
        List<UnitResponse> units = new ArrayList<>();

        for (Unit unit : retrievedUnits) {
            UnitResponse newUnit = mapper.mapUnitResponseFromUnit(unit);

            units.add(newUnit);
        }

        return new GenericResponse<>(new MultipleUnitResponse(units));
    }





    public GenericResponse<Optional<Unit>> getUnitsByUnitId(Long unitId)
    {
        return new GenericResponse<>(repository.findById(unitId));

    }

    public GenericResponse<UnitResponse> getUnitWithId(long id) {
        try {
            return new GenericResponse<>(mapper.mapUnitResponseFromUnit( repository.findById(id).orElseThrow(()
                    -> new InvalidIdException("Company", id))));
        } catch (InvalidIdException e) {
            return new GenericResponse<>(new GenericError(1, "Invalid id", e.getMessage()));
        }
    }

}
