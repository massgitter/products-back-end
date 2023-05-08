package com.openfabric.openfabricbackend.repositories;

import com.openfabric.openfabricbackend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    Product findByName(String name);
    @Query("select distinct p from Product p order by p.id")
    List<Product> getAll();
}
