package vn.com.devmaster.service.managermaterial.reponsitory;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.managermaterial.domain.Customer;
import vn.com.devmaster.service.managermaterial.domain.PaymentMethod;

import java.util.List;

@Repository
public interface PaymentRespon extends CrudRepository<PaymentMethod,Integer> {
    @Query(value = "select * from payment_method",nativeQuery = true)
    List<PaymentMethod> getPayment();

    @Query(value = "select * from payment_method where ISACTIVE = 1",nativeQuery = true)
    List<PaymentMethod> getPaymentMethod();
    @Query(value = "select p from PaymentMethod p where p.id = :idpayment")
    PaymentMethod findAllById(@Param("idpayment") Integer idpayment);
}
