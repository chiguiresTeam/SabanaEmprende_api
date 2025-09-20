package com.hackaton.SabanaEmprende_api.Companies.Controller;

import com.hackaton.SabanaEmprende_api.Common.Components.JwtUtil;
import com.hackaton.SabanaEmprende_api.Companies.Service.CompaniesService;
import com.hackaton.SabanaEmprende_api.Companies.dto.CompaniesDto;
import com.hackaton.SabanaEmprende_api.Companies.dto.CompaniesResDto;
import com.hackaton.SabanaEmprende_api.Products.dto.ProductsResDto;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompaniesController {
    private final CompaniesService companiesService;
    private final JwtUtil jwtUtil;
    @PostMapping
    public ResponseEntity<CompaniesResDto> createCompanies(@RequestBody CompaniesDto dto, @CookieValue("access_token") String token) {
        Long peopleId = jwtUtil.extractUserId(token);
        CompaniesResDto companiesRes = companiesService.createCompanies(dto, peopleId);
        return ResponseEntity.created(URI.create("/companies/" + companiesRes.getId())).body(companiesRes);
    }

    @GetMapping
    public ResponseEntity<Page<CompaniesResDto>> getCompanies(Pageable pageable){
        Page<CompaniesResDto> companies = companiesService.getAllCompanies(pageable);
        return ResponseEntity.ok(companies);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompaniesResDto> updateCompanies(@RequestBody CompaniesDto dto, @PathVariable("id") UUID id) {
        CompaniesResDto companiesRes = companiesService.updateCompanies(dto, id);
        return ResponseEntity.ok(companiesRes);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCompanies(@RequestParam UUID id) {
        companiesService.deleteCompanies(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me/products")
    public ResponseEntity<List<ProductsResDto>> getMyProducts (@CookieValue("access_token") String token){
        Long personId = jwtUtil.extractUserId(token);
        List<ProductsResDto> response = companiesService.getMyProducts(personId);
        return ResponseEntity.ok(response);
    }
}
