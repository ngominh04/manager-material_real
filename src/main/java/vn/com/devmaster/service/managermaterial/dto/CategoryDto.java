package vn.com.devmaster.service.managermaterial.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link vn.com.devmaster.service.managermaterial.domain.Category}
 */
@Value
public class CategoryDto implements Serializable {
    Integer id;
    String name;
    String notes;
    String icon;
    Byte isactive;
}