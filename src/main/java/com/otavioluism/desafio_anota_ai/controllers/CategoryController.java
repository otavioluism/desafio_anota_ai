package com.otavioluism.desafio_anota_ai.controllers;

import com.otavioluism.desafio_anota_ai.DTOs.CategoryDTO;
import com.otavioluism.desafio_anota_ai.entities.category.Category;
import com.otavioluism.desafio_anota_ai.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> insert(@RequestBody CategoryDTO categoryData){
        Category newCategory = this.categoryService.insert(categoryData);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAll(){
        List<Category> listCategories = this.categoryService.getAll();
        return ResponseEntity.ok().body(listCategories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable(name = "id") String id, @RequestBody CategoryDTO categoryData) {
        Category categorySelect = this.categoryService.update(id, categoryData);
        return ResponseEntity.ok().body(categorySelect);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable(name = "id") String id) {
        this.categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
