package com.openfabric.openfabricbackend.Controllers;

import com.openfabric.openfabricbackend.requests.ProductRequest;
import com.openfabric.openfabricbackend.responses.ProductResponse;
import com.openfabric.openfabricbackend.services.ProductService;
import com.openfabric.openfabricbackend.utils.RoleEnum;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@Tag(name = "product")
@AllArgsConstructor
@RequestMapping("/products")
@SecurityRequirement(name = "openfabric")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create")
    public ProductResponse create(@RequestBody ProductRequest request) {
        return productService.createProduct(request);
    }

    @PutMapping("/edit/{id}")
    public ProductResponse update(@PathVariable Long id, @RequestBody ProductRequest request) {
        return productService.editProduct(id, request);
    }

    @GetMapping("/{id}")
    public ProductResponse findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping("/byName/{name}")
    public ProductResponse findByName(@PathVariable String name) {
        return productService.findByName(name);
    }

    @GetMapping("/all")
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }
}
