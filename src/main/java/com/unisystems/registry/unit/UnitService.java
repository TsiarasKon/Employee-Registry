package com.unisystems.registry.unit;


import com.unisystems.registry.GenericError;
import com.unisystems.registry.GenericResponse;
import com.unisystems.registry.InvalidIdException;
import com.unisystems.registry.department.Department;
import com.unisystems.registry.department.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UnitService {
    @Autowired
    private UnitMapper mapper;
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    public UnitService(UnitMapper mapper, UnitRepository unitRepository, DepartmentRepository departmentRepository) {
        this.mapper = mapper;
        this.unitRepository = unitRepository;
        this.departmentRepository = departmentRepository;
    }

    public GenericResponse<MultipleUnitResponse> getAllUnits() {
        Iterable<Unit> retrievedUnits = unitRepository.findAll();
        List<UnitResponse> units = new ArrayList<>();

        for (Unit unit : retrievedUnits) {
            UnitResponse newUnit = mapper.mapUnitResponseFromUnit(unit);

            units.add(newUnit);
        }

        return new GenericResponse<>(new MultipleUnitResponse(units));
    }


    public GenericResponse<UnitResponse> getUnitWithId(long id) {
        try {
            return new GenericResponse<>(mapper.mapUnitResponseFromUnit( unitRepository.findById(id).orElseThrow(()
                    -> new InvalidIdException("Unit", id))));
        } catch (InvalidIdException e) {
            return new GenericResponse<>(new GenericError(1, "Invalid id", e.getMessage()));
        }
    }

    public Unit post(UnitRequest unitRequest) throws InvalidIdException {
        Department unitDept = departmentRepository.findById(unitRequest.getDepartmentId()).orElseThrow(()
                -> new InvalidIdException("Department", unitRequest.getDepartmentId()));
        return unitRepository.save(new Unit(unitRequest.getUnitName(), unitDept));
    }

    public Unit put(UnitRequest unitRequest, long id) throws InvalidIdException {
        Unit unit = unitRepository.findById(id).orElseThrow(()
                -> new InvalidIdException("Unit", id));
        Department unitDept = departmentRepository.findById(unitRequest.getDepartmentId()).orElseThrow(()
                -> new InvalidIdException("Department", unitRequest.getDepartmentId()));
        unit.setName(unitRequest.getUnitName());
        unit.setDept(unitDept);
        return unitRepository.save(unit);
    }

    public Unit patch(UnitRequest unitRequest, long id) throws InvalidIdException {
        Unit unit = unitRepository.findById(id).orElseThrow(()
                -> new InvalidIdException("Unit", id));
        if (unitRequest.getDepartmentId() > 0) {
            Department unitDept = departmentRepository.findById(unitRequest.getDepartmentId()).orElseThrow(()
                    -> new InvalidIdException("Department", unitRequest.getDepartmentId()));
            unit.setDept(unitDept);
        }
        if (unitRequest.getUnitName() != null) {
            unit.setName(unitRequest.getUnitName());
        }
        return unitRepository.save(unit);
    }
}
