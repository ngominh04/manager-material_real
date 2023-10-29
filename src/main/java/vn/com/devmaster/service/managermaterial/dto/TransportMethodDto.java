package vn.com.devmaster.service.managermaterial.dto;

import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link vn.com.devmaster.service.managermaterial.domain.TransportMethod}
 */
@Value
public class TransportMethodDto implements Serializable {
    Integer id;
    String name;
    String notes;
    Instant createdDate;
    Instant updatedDate;
    Byte isactive;
}