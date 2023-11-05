package vn.com.devmaster.service.managermaterial.reponsitory;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.managermaterial.domain.PaymentMethod;
import vn.com.devmaster.service.managermaterial.domain.TransportMethod;

import java.util.List;

@Repository
public interface TransportRespon extends CrudRepository<TransportMethod,Integer> {
//    @Query(value = "select * from transport_method",nativeQuery = true)
//    List<TransportMethod> getTransport();
}
