package vn.com.devmaster.service.managermaterial.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "nguoinhan")
public class Nguoinhan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_customer")
    private Customer idCustomer;

    @Size(max = 250)
    @Column(name = "name", length = 250)
    private String name;

    @Size(max = 250)
    @Column(name = "address", length = 250)
    private String address;

    @Size(max = 50)
    @Column(name = "phone", length = 50)
    private String phone;

}