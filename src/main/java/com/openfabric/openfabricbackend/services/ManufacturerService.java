package com.openfabric.openfabricbackend.services;

import com.openfabric.openfabricbackend.models.Manufacturer;
import com.openfabric.openfabricbackend.repositories.ManufacturerRepo;
import com.openfabric.openfabricbackend.requests.CompanyRequest;
import com.openfabric.openfabricbackend.responses.CompanyResponse;
import com.openfabric.openfabricbackend.utils.CompanyEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ManufacturerService {
    private final ManufacturerRepo manufacturerRepo;
    private final CountryService countryService;

    public void createOnInit(String name, String phone, String email, Long id) {
        Manufacturer response = manufacturerRepo.findByName(name);
        if (response == null) {
            Manufacturer manufacturer = Manufacturer.builder()
                    .name(name)
                    .phone(phone)
                    .email(email)
                    .country(countryService.findById(id))
                    .build();
            manufacturerRepo.save(manufacturer);
        }

    }

    public void onInit() {
        Arrays.stream(CompanyEnum.values()).forEach(companyEnum -> {
            createOnInit(companyEnum.getName(), companyEnum.getPhone(), companyEnum.getEmail(), companyEnum.getCountry());
        });
    }

    public CompanyResponse create(CompanyRequest request) {
        Manufacturer manufacturer = Manufacturer.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .email(request.getEmail())
                .country(countryService.findById(request.getCountry()))
                .build();
        return manufacturerRepo.save(manufacturer).companyResponse();
    }

    public CompanyResponse update(Long id, Manufacturer request) {
        Manufacturer manufacturer = manufacturerRepo.findById(id).orElseThrow(() -> new RuntimeException("No company found with id: " + id));
        manufacturer.setName(request.getName());
        manufacturer.setPhone(request.getPhone());
        manufacturer.setEmail(request.getEmail());
        manufacturer.setCountry(request.getCountry());
        return manufacturerRepo.save(manufacturer).companyResponse();
    }

    public CompanyResponse findByName(String name) {
        return manufacturerRepo.findByName(name).companyResponse();
    }

    public Manufacturer findById(long id) {
        return manufacturerRepo.findById(id).orElseThrow(() -> new RuntimeException("No company found with id: " + id));
    }

    public List<CompanyResponse> getAllCompanies() {
        return manufacturerRepo.findAll().stream().map(Manufacturer::companyResponse).collect(Collectors.toList());
    }
}
