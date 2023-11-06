package vn.com.devmaster.service.managermaterial.reponsitory;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import vn.com.devmaster.service.managermaterial.domain.OrdersPayment;
import vn.com.devmaster.service.managermaterial.domain.PaymentMethod;

import java.util.List;


public interface OrderPaymentRespon extends CrudRepository<OrdersPayment, Integer> {

}
