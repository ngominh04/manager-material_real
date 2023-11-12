package vn.com.devmaster.service.managermaterial.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.managermaterial.domain.Product;
import vn.com.devmaster.service.managermaterial.domain.ProductImage;
import vn.com.devmaster.service.managermaterial.projecttion.IProduct;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRespon extends CrudRepository<Product, Integer> {

    Product findAllById(Integer id);

//    List<Product> findAllBy(Integer id);
    @Query(value = "select * from product",nativeQuery = true)
    List<Product> getAllProduct();

}
