package com.otavioluism.desafio_anota_ai.entities.category;


import com.otavioluism.desafio_anota_ai.DTOs.CategoryDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
@Data
@NoArgsConstructor
public class Category {

    @Id
    private String id;
    private String title;
    private String description;
    private String ownerId;

    public Category(CategoryDTO categoryData){
        this.title = categoryData.title();
        this.ownerId = categoryData.ownerId();
        this.description = categoryData.description();
    }

}
