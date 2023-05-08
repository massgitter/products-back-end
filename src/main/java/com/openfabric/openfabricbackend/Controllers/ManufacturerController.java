package com.openfabric.openfabricbackend.Controllers;

import com.openfabric.openfabricbackend.models.Country;
import com.openfabric.openfabricbackend.models.Manufacturer;
import com.openfabric.openfabricbackend.requests.CompanyRequest;
import com.openfabric.openfabricbackend.responses.CompanyResponse;
import com.openfabric.openfabricbackend.services.CountryService;
import com.openfabric.openfabricbackend.services.ManufacturerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@Tag(name = "manufacturer")
@AllArgsConstructor
@RequestMapping("/manufacturer")
@SecurityRequirement(name = "openfabric")
public class ManufacturerController {
    private final ManufacturerService manufacturerService;
    private final CountryService countryService;

    @PostMapping("/create")
    public CompanyResponse create(@RequestBody CompanyRequest request) {
        return manufacturerService.create(request);
    }

    @PutMapping("/edit")
    public CompanyResponse update(Long id, @RequestBody Manufacturer request) {
        return manufacturerService.update(id, request);
    }

    @GetMapping("/{id}")
    public CompanyResponse findById(@PathVariable Long id) {
        return manufacturerService.findById(id).companyResponse();
    }

    @GetMapping("/byName/{name}")
    public CompanyResponse findByName(@PathVariable String name) {
        return manufacturerService.findByName(name);
    }

    @GetMapping("/all")
    public List<CompanyResponse> getAllCompanies() {
        return manufacturerService.getAllCompanies();
    }

    @GetMapping("/countires")
    public List<Country> getAllCountries() {
        return countryService.getAll();
    }
}
