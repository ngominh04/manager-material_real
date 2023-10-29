package vn.com.devmaster.service.managermaterial.dto;

import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link vn.com.devmaster.service.managermaterial.domain.Order}
 */
@Value
public class OrderDto implements Serializable {
    Integer id;
    String idorders;
    Instant ordersDate;
    CustomerDto idcustomer;
    Double totalMoney;
    String notes;
    String nameReciver;
    String address;
    String phone;
}