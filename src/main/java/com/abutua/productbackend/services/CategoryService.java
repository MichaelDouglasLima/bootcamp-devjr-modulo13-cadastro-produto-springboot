package com.abutua.productbackend.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.abutua.productbackend.dto.CategoryRequest;
import com.abutua.productbackend.dto.CategoryResponse;
import com.abutua.productbackend.models.Category;
import com.abutua.productbackend.repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryResponse getDTOById(int id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        return category.toDTO();
    }

    public Category getById(int id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        return category;
    }

    public List<CategoryResponse> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(c -> c.toDTO())
                .collect(Collectors.toList());
    }

    public CategoryResponse save(CategoryRequest categoryRequest) {
        Category category = categoryRepository.save(categoryRequest.toEntity());
        return category.toDTO();
    }

    public void deleteById(int id) {
        Category category = getById(id);
        categoryRepository.delete(category);
    }

    public void update(@PathVariable int id, @RequestBody CategoryRequest categoryUpdate) {
        Category category = getById(id);
        category.setName(categoryUpdate.getName());
        categoryRepository.save(category);
    }
}
