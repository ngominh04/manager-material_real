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
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NAME", length = 500)
    private String name;


    public void setId(Integer id) {
        this.id = id;
    }

    @Lob
    @Column(name = "DESCRIPTION")
    private String description;

    @Lob
    @Column(name = "NOTES")
    private String notes;

    @Column(name = "IMAGE", length = 550)
    private String image;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "IDCATEGORY")
    private Category idcategory;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "QUATITY")
    private Integer quatity;

    @Column(name = "ISDELETE")
    private Integer isDelete;

    @Column(name = "CREATED_DATE")
    private String createdDate;

    @Column(name = "UPDATED_DATE")
    private String updatedDate;

    @Column(name = "CREATED_BY", length = 50)
    private String createdBy;

    @Column(name = "UPDATED_BY", length = 50)
    private String updatedBy;

    @Column(name = "ISACTIVE")
    private Byte isactive;

//    @OneToMany(mappedBy = "product", cascade = CascadeType.DETACH)
//    private List<CartItem> cartItems;

}