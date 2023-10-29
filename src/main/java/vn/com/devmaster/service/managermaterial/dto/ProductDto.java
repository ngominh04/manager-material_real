package vn.com.devmaster.service.managermaterial.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link vn.com.devmaster.service.managermaterial.domain.Product}
 */
@Value
public class ProductDto implements Serializable {
    Integer id;
    String name;
    String description;
    String notes;
    String image;
    CategoryDto idcategory;
    Double price;
    Integer quatity;
}