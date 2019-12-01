package com.unisystems.registry.business_unit;

import com.unisystems.registry.GenericError;
import com.unisystems.registry.GenericResponse;
import com.unisystems.registry.InvalidIdException;
import com.unisystems.registry.company.Company;
import com.unisystems.registry.company.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusinessUnitService
{
    @Autowired
    private BusinessUnitMapper mapper;
    @Autowired
    private BusinessUnitRepository buRepository;
    @Autowired
    private CompanyRepository companyRepository;

    public BusinessUnitService(BusinessUnitMapper mapper, BusinessUnitRepository buRepository) {
        this.mapper = mapper;
        this.buRepository = buRepository;
    }

    public GenericResponse<MultipleBusinessUnitResponse> getAllBusinessUnits()
    {
        Iterable<BusinessUnit> retrievedBusinessUnits = buRepository.findAll();
        List<BusinessUnitResponse> businessUnits = new ArrayList<>();

        for(BusinessUnit businessUnit: retrievedBusinessUnits)
        {
            businessUnits.add(mapper.mapBusinessUnitResponseFromBusinessUnit(businessUnit));
        }
        return new GenericResponse<>(new MultipleBusinessUnitResponse(businessUnits));
    }

    public GenericResponse<Optional<BusinessUnit>> getBusinessUnitById(Long id)
    {
        return new GenericResponse<>(buRepository.findById(id));

    }

    public GenericResponse<BusinessUnitResponse> getBusinessUnitWithId(long id) {
        try {
            return new GenericResponse<>(mapper.mapBusinessUnitResponseFromBusinessUnit( buRepository.findById(id).orElseThrow(()
                    -> new InvalidIdException("Business Unit", id))));
        } catch (InvalidIdException e) {
            return new GenericResponse<>(new GenericError(1, "Invalid id", e.getMessage()));
        }
    }

    public BusinessUnit post(BusinessUnitRequest buRequest) throws InvalidIdException {
        Company buCompany = companyRepository.findById(buRequest.getCompanyId()).orElseThrow(()
                -> new InvalidIdException("Company", buRequest.getCompanyId()));
        return buRepository.save(new BusinessUnit(buRequest.getBusinessUnitName(), buCompany));
    }

    public BusinessUnit put(BusinessUnitRequest buRequest, long id) throws InvalidIdException {
        BusinessUnit bu = buRepository.findById(id).orElseThrow(()
                -> new InvalidIdException("Business Unit", id));
        Company buCompany = companyRepository.findById(buRequest.getCompanyId()).orElseThrow(()
                -> new InvalidIdException("Company", buRequest.getCompanyId()));
        bu.setCompany(buCompany);
        bu.setName(buRequest.getBusinessUnitName());
        return buRepository.save(bu);
    }

    public BusinessUnit patch(BusinessUnitRequest buRequest, long id) throws InvalidIdException {
        BusinessUnit bu = buRepository.findById(id).orElseThrow(()
                -> new InvalidIdException("Business Unit", id));
        if (buRequest.getCompanyId() > 0) {
            Company buCompany = companyRepository.findById(buRequest.getCompanyId()).orElseThrow(()
                    -> new InvalidIdException("Company", buRequest.getCompanyId()));
            bu.setCompany(buCompany);
        }
        if (buRequest.getBusinessUnitName() != null) {
            bu.setName(buRequest.getBusinessUnitName());
        }
        return buRepository.save(bu);
    }
}
