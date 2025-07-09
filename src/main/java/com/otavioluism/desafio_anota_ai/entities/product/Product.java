package com.otavioluism.desafio_anota_ai.entities.product;


import com.otavioluism.desafio_anota_ai.DTOs.ProductDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Data
@NoArgsConstructor
public class Product {

    @Id
    private String id;
    private String title;
    private String description;
    private String ownerId;
    private Integer price;
    private String category;

    public Product(ProductDTO productData){
        this.title = productData.title();
        this.description = productData.description();
        this.ownerId = productData.ownerId();
        this.price = productData.price();
        this.category = productData.categoryId();
    }

    @Override
    public String toString(){
        JSONObject json = new JSONObject();
        json.put("id", this.id);
        json.put("title", this.title);
        json.put("price", this.price);
        json.put("ownerId", this.ownerId);
        json.put("category", this.category);
        json.put("description", this.description);
        json.put("type", "product");

        return json.toString();
    }
}
