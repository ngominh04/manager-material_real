package vn.com.devmaster.service.managermaterial.reponsitory;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.managermaterial.domain.PaymentMethod;
import vn.com.devmaster.service.managermaterial.domain.TransportMethod;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface TransportRespon extends CrudRepository<TransportMethod,Integer> {
    @Query(value = "select * from transport_method",nativeQuery = true)
    List<TransportMethod> getTransport();

    @Query(value = "select * from transport_method where ISACTIVE = 1",nativeQuery = true)
    List<TransportMethod> getTransportMethod();

    @Query(value = "select tp from TransportMethod tp where tp.id= :idtransport ")
    TransportMethod findAllById(@Param("idtransport")Integer idtransport);
}
