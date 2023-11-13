package vn.com.devmaster.service.managermaterial.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor

@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Size(max = 250)
    @Column(name = "NAME", length = 250)
    private String name;

    @Size(max = 50)
    @Column(name = "USERNAME", length = 50)
    private String username;

    @Size(max = 50)
    @Column(name = "PASSWORD", length = 50)
    private String password;

    @Size(max = 500)
    @Column(name = "ADDRESS", length = 500)
    private String address;

    @Size(max = 150)
    @Column(name = "EMAIL", length = 150)
    private String email;

    @Size(max = 50)
    @Column(name = "PHONE", length = 50)
    private String phone;

    @Column(name = "CREATED_DATE")
    private String createdDate;

    @Column(name = "ISACTIVE")
    private Byte isactive;

    @Column(name = "phan_quyen")
    private Byte phanquyen;

    @OneToMany(mappedBy = "idcustomer", cascade = CascadeType.ALL)
    private List<Order> orders;

//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
//    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "idCustomer", cascade = CascadeType.PERSIST)
    private List<Nguoinhan> nguoinhans;

//
//    public Customer() {
//        this.shopingcart = new ShopingCart();
//    }
}