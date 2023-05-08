package com.openfabric.openfabricbackend.services;

import com.openfabric.openfabricbackend.models.Country;
import com.openfabric.openfabricbackend.repositories.CountryRepo;
import com.openfabric.openfabricbackend.utils.CountryEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class CountryService {
    private final CountryRepo countryRepo;

    public void create(String name, String city) {
        Country country = findByName(name);
        if (country == null) {
            Country country1 = Country.builder()
                    .name(name)
                    .city(city)
                    .build();
            countryRepo.save(country1);
        }
    }

    private Country findByName(String name) {
        return countryRepo.findByName(name);
    }

    public void onInit() {
        Arrays.stream(CountryEnum.values()).forEach(countryEnum -> {
            create(countryEnum.getName(), countryEnum.getCity());
        });
    }

    public Country findById(Long id) {
        return countryRepo.findById(id).orElseThrow(() -> new RuntimeException("No country found with di: " + id));
    }
    public List<Country> getAll() {
        return countryRepo.findAll();
    }
}
