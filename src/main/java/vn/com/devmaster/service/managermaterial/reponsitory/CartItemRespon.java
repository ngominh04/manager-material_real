package vn.com.devmaster.service.managermaterial.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.managermaterial.domain.CartItem;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRespon extends CrudRepository<CartItem, Integer> {

    CartItem findAllById(Integer id);

    @Query(value = "select c.* from CartItem c where c.username = ?",nativeQuery = true)
    List<CartItem> findByUsername(String username);
    @Query(value = "select * from cartitem where id_customer = ?",nativeQuery = true)
    List<CartItem> getById(Integer id);

//    @Query(value = "select c.* from CartItem c where c.product.id = ?1 and c.customer.id = ?2",nativeQuery = true)
//    CartItem findAllById_username(Integer idPro,Integer idCus);
}
