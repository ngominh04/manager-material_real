package vn.com.devmaster.service.managermaterial.domain;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NAME", length = 500)
    private String name;

    public Integer getId() {
        return id;
    }

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDCATEGORY")
    private Category idcategory;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "QUATITY")
    private Integer quatity;

    @Column(name = "CREATED_DATE")
    private Instant createdDate;

    @Column(name = "UPDATED_DATE")
    private Instant updatedDate;

    @Column(name = "CREATED_BY", length = 50)
    private String createdBy;

    @Column(name = "UPDATED_BY", length = 50)
    private String updatedBy;

    @Column(name = "ISACTIVE")
    private Byte isactive;



}