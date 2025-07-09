package com.otavioluism.desafio_anota_ai.services;

import com.amazonaws.services.sns.AmazonSNS;
import com.otavioluism.desafio_anota_ai.DTOs.CategoryDTO;
import com.otavioluism.desafio_anota_ai.DTOs.MessageTopicDTO;
import com.otavioluism.desafio_anota_ai.entities.category.Category;
import com.otavioluism.desafio_anota_ai.repositories.CategoryRepository;
import com.otavioluism.desafio_anota_ai.exceptions.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AwsSnsService snsService;

    public Category insert(CategoryDTO categoryData){
        Category newCategory = new Category(categoryData);
        this.categoryRepository.save(newCategory);
        this.snsService.publish(new MessageTopicDTO(newCategory.toString()));
        return newCategory;
    }

    public List<Category> getAll(){
        return this.categoryRepository.findAll();
    }

    public Category update(String id, CategoryDTO categoryData){
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        if (!categoryData.title().isEmpty()) category.setTitle(categoryData.title());
        if (!categoryData.description().isEmpty()) category.setDescription(categoryData.description());

        this.categoryRepository.save(category);

        this.snsService.publish(new MessageTopicDTO(category.toString()));

        return category;
    }

    public void delete(String id) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        this.categoryRepository.delete(category);
    }

    public Optional<Category> getById(String id){
        return this.categoryRepository.findById(id);
    }


}
