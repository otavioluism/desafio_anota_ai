package com.otavioluism.desafio_anota_ai.repositories;

import com.otavioluism.desafio_anota_ai.entities.category.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
}
