package vn.com.devmaster.service.managermaterial.domain;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product_images")
public class ProductImage {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NAME", length = 250)
    private String name;

    @Column(name = "URL", length = 250)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PRODUCT")
    private Product idProduct;

}