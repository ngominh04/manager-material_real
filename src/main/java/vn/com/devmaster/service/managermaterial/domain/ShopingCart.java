package vn.com.devmaster.service.managermaterial.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "shoping_cart")
public class ShopingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id",referencedColumnName = "ID")
    private Customer customer;

//    private double totalPrice;

//    private int totalItems;

//    @OneToMany(cascade = CascadeType.DETACH, mappedBy = "cart")
//    private Set<CartItem> cartItems;
//    public ShopingCart() {
////        this.totalItems = 0;
////        this.totalPrice = 0.0;
//        this.cartItems = new HashSet<>();
//    }

}