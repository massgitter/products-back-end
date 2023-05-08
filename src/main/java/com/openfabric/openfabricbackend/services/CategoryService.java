package com.openfabric.openfabricbackend.services;

import com.openfabric.openfabricbackend.models.Category;
import com.openfabric.openfabricbackend.repositories.CategoryRepo;
import com.openfabric.openfabricbackend.utils.CategoryEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepo categoryRepo;

    public Category create(String request) {
        Category savedCategory = findByName(request);
        Category category = Category.builder()
                .name(request)
                .build();
        try {
            if (savedCategory == null) {
                return categoryRepo.save(category);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Category findByName(String name) {
        return categoryRepo.findByName(name);
    }
    public void onInit() {
        Arrays.stream(CategoryEnum.values()).forEach(categoryEnum -> {
            create(categoryEnum.getCode());
        });
    }

    public Category findById(Long id) {
        return categoryRepo.findById(id).orElseThrow(() -> new RuntimeException("no Category found with id: " + id));

    }

    public Category update(Long id, String request) {
        Category category = findById(id);
        category.setName(request);
        return categoryRepo.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }
}
