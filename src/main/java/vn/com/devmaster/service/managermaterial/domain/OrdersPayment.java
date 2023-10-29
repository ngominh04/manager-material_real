package vn.com.devmaster.service.managermaterial.domain;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders_payment")
public class OrdersPayment {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDORD")
    private Order idord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDPAYMENT")
    private PaymentMethod idpayment;

    @Column(name = "TOTAL")
    private Integer total;

    @Column(name = "NOTES")
    private Integer notes;

    @Column(name = "STATUS", length = 50)
    private String status;

}