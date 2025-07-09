package com.otavioluism.desafio_anota_ai.services;

import com.otavioluism.desafio_anota_ai.DTOs.CategoryDTO;
import com.otavioluism.desafio_anota_ai.DTOs.MessageTopicDTO;
import com.otavioluism.desafio_anota_ai.DTOs.ProductDTO;
import com.otavioluism.desafio_anota_ai.entities.category.Category;
import com.otavioluism.desafio_anota_ai.entities.product.Product;
import com.otavioluism.desafio_anota_ai.exceptions.CategoryNotFoundException;
import com.otavioluism.desafio_anota_ai.exceptions.ProductNotFoundException;
import com.otavioluism.desafio_anota_ai.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AwsSnsService snsService;

    public Product insert(ProductDTO productData){
        this.categoryService.getById(productData.categoryId())
                .orElseThrow(CategoryNotFoundException::new);

        Product newProduct = new Product(productData);
        this.productRepository.save(newProduct);
        this.snsService.publish(new MessageTopicDTO(newProduct.toString()));
        return newProduct;
    }

    public List<Product> getAll(){
        return this.productRepository.findAll();
    }

    public Product update(String id, ProductDTO productData){
        Product product = this.productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        if(productData.categoryId() != null){
            this.categoryService.getById(productData.categoryId()).ifPresentOrElse(
                    category -> {product.setCategory(category.getId());},
                    () -> {throw new CategoryNotFoundException();}
            );
        }

        if (!productData.title().isEmpty()) product.setTitle(productData.title());
        if (!productData.description().isEmpty()) product.setDescription(productData.description());
        if (!(productData.price() == null)) product.setPrice(productData.price());

        this.productRepository.save(product);

        this.snsService.publish(new MessageTopicDTO(product.toString()));

        return product;
    }

    public void delete(String id) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        this.productRepository.delete(product);
    }
}
