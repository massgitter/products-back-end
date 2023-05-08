package com.openfabric.openfabricbackend.repositories;

import com.openfabric.openfabricbackend.models.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepo extends JpaRepository<Manufacturer, Long> {
    Manufacturer findByName(String name);
}
