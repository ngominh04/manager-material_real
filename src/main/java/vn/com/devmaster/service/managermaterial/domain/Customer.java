package vn.com.devmaster.service.managermaterial.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;


    @NotEmpty
    @Column(name = "NAME", length = 250)
    private String name;

    @NotEmpty
    @Column(name = "USERNAME", length = 50)
    private String username;

    @NotEmpty
    @Column(name = "PASSWORD", length = 50)
    private String password;

    @NotEmpty
    @Column(name = "ADDRESS", length = 500)
    private String address;

    @NotEmpty
    @Column(name = "EMAIL", length = 150)
    private String email;

    @NotEmpty
    @Column(name = "PHONE", length = 50)
    private String phone;

    @Column(name = "CREATED_DATE")
    private String createdDate;

    @Column(name = "ISACTIVE")
    private Byte isactive;

}