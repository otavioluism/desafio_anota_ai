package com.otavioluism.desafio_anota_ai.repositories;

import com.otavioluism.desafio_anota_ai.entities.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
