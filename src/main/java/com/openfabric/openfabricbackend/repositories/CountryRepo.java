package com.openfabric.openfabricbackend.repositories;

import com.openfabric.openfabricbackend.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepo extends JpaRepository<Country, Long> {
    Country findByName(String name);
}
