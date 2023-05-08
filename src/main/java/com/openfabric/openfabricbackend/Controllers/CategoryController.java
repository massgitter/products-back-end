package com.openfabric.openfabricbackend.Controllers;

import com.openfabric.openfabricbackend.models.Category;
import com.openfabric.openfabricbackend.services.CategoryService;
import com.openfabric.openfabricbackend.utils.RoleEnum;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@Tag(name = "category")
@RequestMapping("/categories")
@SecurityRequirement(name = "openfabric")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/create")
    public Category create(@RequestBody String request) {
        return categoryService.create(request);
    }

    @PutMapping("/edit")
    public Category update(Long id, @RequestBody String request) {
        return categoryService.update(id, request);
    }

    @GetMapping("/{id}")
    public Category findById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @GetMapping("/byName/{name}")
    public Category findByName(@PathVariable String name) {
        return categoryService.findByName(name);
    }

    @GetMapping("/all")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
