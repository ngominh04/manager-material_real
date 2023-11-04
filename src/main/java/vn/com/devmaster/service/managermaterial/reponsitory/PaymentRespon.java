package vn.com.devmaster.service.managermaterial.reponsitory;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.managermaterial.domain.Customer;
import vn.com.devmaster.service.managermaterial.domain.PaymentMethod;

import java.util.List;

@Repository
public interface PaymentRespon extends CrudRepository<PaymentMethod,Integer> {
    @Query(value = "select * from payment_method",nativeQuery = true)
    List<PaymentMethod> getPayment();
}
