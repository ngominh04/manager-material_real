package vn.com.devmaster.service.managermaterial.reponsitory;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.managermaterial.domain.ProductImage;

import java.util.List;

@Repository
public interface ImageProductRespon extends CrudRepository<ProductImage,Integer> {
    @Query(value = "select  * from product_images",nativeQuery = true)
    List<ProductImage> getProductImage();
}
