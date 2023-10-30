package vn.com.devmaster.service.managermaterial.domain;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "IDORDERS", length = 10)
    private String idorders;

    @Column(name = "ORDERS_DATE")
    private Instant ordersDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDCUSTOMER")
    private Customer idcustomer;

    @Column(name = "TOTAL_MONEY")
    private Double totalMoney;

    @Lob
    @Column(name = "NOTES")
    private String notes;

    @Column(name = "NAME_RECIVER", length = 250)
    private String nameReciver;

    @Column(name = "ADDRESS", length = 500)
    private String address;

    @Column(name = "PHONE", length = 50)
    private String phone;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "idord")
    private List<OrdersDetail> ordersDetails;

}