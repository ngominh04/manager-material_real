package vn.com.devmaster.service.managermaterial.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link vn.com.devmaster.service.managermaterial.domain.OrdersTransport}
 */
@Value
public class OrdersTransportDto implements Serializable {
    Integer id;
    List<TransportMethodDto> dtos;
    Integer total;
    Integer notes;
}