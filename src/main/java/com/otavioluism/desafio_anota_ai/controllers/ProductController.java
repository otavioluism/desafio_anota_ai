package com.otavioluism.desafio_anota_ai.controllers;

import com.otavioluism.desafio_anota_ai.DTOs.ProductDTO;
import com.otavioluism.desafio_anota_ai.entities.product.Product;
import com.otavioluism.desafio_anota_ai.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody ProductDTO productData){
        Product newProduct = this.productService.insert(productData);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        List<Product> listProducts = this.productService.getAll();
        return ResponseEntity.ok().body(listProducts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable(name = "id") String id, @RequestBody ProductDTO productData) {
        Product productSelected = this.productService.update(id, productData);
        return ResponseEntity.ok().body(productSelected);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable(name = "id") String id) {
        this.productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
