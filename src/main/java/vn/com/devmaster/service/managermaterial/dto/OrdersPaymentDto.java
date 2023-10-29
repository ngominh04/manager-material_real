package vn.com.devmaster.service.managermaterial.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link vn.com.devmaster.service.managermaterial.domain.OrdersPayment}
 */
@Value
public class OrdersPaymentDto implements Serializable {
    Integer id;
    List<PaymentMethodDto> dtos;
    Integer total;
    Integer notes;
    String status;
}