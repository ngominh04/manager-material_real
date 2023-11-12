package vn.com.devmaster.service.managermaterial.reponsitory;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.managermaterial.domain.Customer;
import vn.com.devmaster.service.managermaterial.domain.Nguoinhan;
import vn.com.devmaster.service.managermaterial.domain.PaymentMethod;
import vn.com.devmaster.service.managermaterial.spl.Sql;

import java.util.List;

@Repository
public interface NguoiNhanRespon extends CrudRepository<Nguoinhan, Integer> {
    @Query(value = "select  * from nguoinhan where id_customer = ?",nativeQuery = true)
    List<Nguoinhan> getNguoinhan(Integer idCustomer);
    @Query(value = "select p from Nguoinhan p where p.id = :idnguoiNhan")
    Nguoinhan findAllById(@Param("idnguoiNhan") Integer idnguoiNhan);

}
