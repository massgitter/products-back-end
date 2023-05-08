package com.openfabric.openfabricbackend.services;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class OnInitServices {
    private final RoleService roleService;
    private final ProductService productService;
    private final CountryService countryService;
    private final CategoryService categoryService;
    private JwtUserDetailsService jwtUserDetailsService;
    private final ManufacturerService manufacturerService;

    @Bean
    public CommandLineRunner onInit() {
        return args -> {
            System.out.println("<----------------------------------Executing On init tasks----------------------------------->");
            roleService.onInit();
            countryService.onInit();
            categoryService.onInit();
            manufacturerService.onInit();
            productService.onInit();
            jwtUserDetailsService.onInit();
        };
    }
}
