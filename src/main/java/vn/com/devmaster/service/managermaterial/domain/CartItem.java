package vn.com.devmaster.service.managermaterial.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "cartitem")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 250)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "image", length = 250)
    private String image;

    @Column(name = "username", length = 250)
    private String username;

//    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "id_customer")
    private Integer idCustomer;


//    @ManyToOne( cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_product")
    private Integer idProduct;

}