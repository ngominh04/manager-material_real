package vn.com.devmaster.service.managermaterial.reponsitory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.managermaterial.domain.OrdersTransport;

@Repository
public interface OrderTransportRespon extends CrudRepository<OrdersTransport,Integer> {
}
