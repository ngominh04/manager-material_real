package vn.com.devmaster.service.managermaterial.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link vn.com.devmaster.service.managermaterial.domain.OrdersDetail}
 */
@Value
public class OrdersDetailDto implements Serializable {
    Integer id;
    List<ProductDto> dtos;
    Double price;
    Integer qty;
}