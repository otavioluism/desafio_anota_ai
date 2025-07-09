package com.otavioluism.desafio_anota_ai.entities.category;


import com.otavioluism.desafio_anota_ai.DTOs.CategoryDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.json.JsonObject;
import org.json.JSONObject;
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

    @Override
    public String toString(){
        JSONObject json = new JSONObject();
        json.put("id", this.id);
        json.put("title", this.title);
        json.put("ownerId", this.ownerId);
        json.put("description", this.description);
        json.put("type", "category");

        return json.toString();
    }
}
