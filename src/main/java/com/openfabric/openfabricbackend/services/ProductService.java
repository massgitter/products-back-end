package com.openfabric.openfabricbackend.services;

import com.openfabric.openfabricbackend.models.Product;
import com.openfabric.openfabricbackend.repositories.ProductRepo;
import com.openfabric.openfabricbackend.requests.ProductRequest;
import com.openfabric.openfabricbackend.responses.ProductResponse;
import com.openfabric.openfabricbackend.utils.ProductEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;
    private CategoryService categoryService;
    private final ManufacturerService companService;

    private void createOnInit(String name, String color, int size, Long category, Long company) {
        Product product = Product.builder()
                .name(name)
                .color(color)
                .size(size)
                .category(categoryService.findById(category))
                .manufacturer(companService.findById(company))
                .build();
        productRepo.save(product);
    }

    public void onInit() {
        Arrays.stream(ProductEnum.values()).forEach(productEnum -> {
            createOnInit(productEnum.getName(), productEnum.getColor(), productEnum.getSize(), productEnum.getCategory(), productEnum.getManufacturer());
        });
    }

    public ProductResponse createProduct(ProductRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .color(request.getColor())
                .size(request.getSize())
                .manufacturer(companService.findById(request.getCompany()))
                .category(categoryService.findById(request.getCategory()))
                .build();
        return productRepo.save(product).productResponse();
    }

    public ProductResponse editProduct(Long id, ProductRequest request) {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("No product found with the given id: " + id));
        product.setName(request.getName());
        product.setColor(request.getColor());
        product.setSize(request.getSize());
        product.setUpdatedOn(java.util.Date.from(Instant.now()));
        product.setManufacturer(companService.findById(request.getCompany()));
        product.setCategory(categoryService.findById(request.getCategory()));
        return productRepo.save(product).productResponse();
    }

    public ProductResponse findById(Long id) {
        return productRepo.findById(id).orElseThrow(() -> new RuntimeException("No product found with the given id: " + id)).productResponse();
    }

    public ProductResponse findByName(String name) {
        return productRepo.findByName(name).productResponse();
    }

    public List<ProductResponse> getAllProducts() {
        return productRepo.getAll().stream().map(Product::productResponse).collect(Collectors.toList());
    }
}
