package vn.com.devmaster.service.managermaterial.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.managermaterial.domain.Order;
import vn.com.devmaster.service.managermaterial.domain.OrdersDetail;
import vn.com.devmaster.service.managermaterial.projecttion.IOrderDetail;
import vn.com.devmaster.service.managermaterial.spl.Sql;

import java.util.List;

@Repository
public interface OderDetailRespon extends JpaRepository<OrdersDetail,Integer> {
    @Query(value = "select o from Order o where o.idcustomer.id = ?1")
    List<Order> findAllByCustomerId(Long id);

    @Query(value = Sql.ORDERDETAIL,nativeQuery = true)
    List<IOrderDetail> getOrdersDetailById(Integer idOrder);
}
