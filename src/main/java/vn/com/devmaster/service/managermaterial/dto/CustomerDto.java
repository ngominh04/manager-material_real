package vn.com.devmaster.service.managermaterial.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link vn.com.devmaster.service.managermaterial.domain.Customer}
 */
@Value
public class CustomerDto implements Serializable {
    Integer id;
    String name;
    String username;
    String password;
    String address;
    String email;
    String phone;
}