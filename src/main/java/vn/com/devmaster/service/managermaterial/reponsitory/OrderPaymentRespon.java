package vn.com.devmaster.service.managermaterial.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.managermaterial.domain.OrdersPayment;
import vn.com.devmaster.service.managermaterial.domain.PaymentMethod;
import vn.com.devmaster.service.managermaterial.projecttion.IPayment_method;

public interface OrderPaymentRespon extends JpaRepository<OrdersPayment, Integer> {
}
