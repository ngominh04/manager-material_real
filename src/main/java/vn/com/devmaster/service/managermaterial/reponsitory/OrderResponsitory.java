package vn.com.devmaster.service.managermaterial.reponsitory;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.managermaterial.domain.Order;

import java.util.Optional;

@Repository
public interface OrderResponsitory extends CrudRepository<Order, Integer> {
    @Query(value = "select o from Order o where o.idorders= :idOder")
    Order getOrder(@Param("idOder") Integer idOder);
}
