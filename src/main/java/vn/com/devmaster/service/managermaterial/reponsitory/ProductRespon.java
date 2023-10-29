package vn.com.devmaster.service.managermaterial.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.managermaterial.domain.Product;
import vn.com.devmaster.service.managermaterial.domain.ProductImage;
import vn.com.devmaster.service.managermaterial.projecttion.IProduct;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRespon extends JpaRepository<Product, Integer> {

    Product findAllById(Integer id);

}
