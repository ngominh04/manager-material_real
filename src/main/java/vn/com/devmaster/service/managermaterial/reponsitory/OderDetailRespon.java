package vn.com.devmaster.service.managermaterial.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.managermaterial.domain.Order;
import vn.com.devmaster.service.managermaterial.domain.OrdersDetail;

import java.util.List;

@Repository
public interface OderDetailRespon extends JpaRepository<OrdersDetail,Integer> {
    @Query("select o from Order o where o.idcustomer.id = ?1")
    List<Order> findAllByCustomerId(Long id);
}
