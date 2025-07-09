package com.otavioluism.desafio_anota_ai.entities.product;


import com.otavioluism.desafio_anota_ai.DTOs.ProductDTO;
import com.otavioluism.desafio_anota_ai.entities.category.Category;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private Category category;

    public Product(ProductDTO productData){
        this.title = productData.title();
        this.description = productData.description();
        this.ownerId = productData.ownerId();
        this.price = productData.price();
    }
}
