package vn.com.devmaster.service.managermaterial.reponsitory;

import org.springframework.data.repository.CrudRepository;
import vn.com.devmaster.service.managermaterial.domain.OrdersPayment;


public interface OrderPaymentRespon extends CrudRepository<OrdersPayment, Integer> {
}
