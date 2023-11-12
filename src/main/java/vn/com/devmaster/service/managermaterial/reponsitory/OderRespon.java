package vn.com.devmaster.service.managermaterial.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.managermaterial.domain.Order;
import vn.com.devmaster.service.managermaterial.projecttion.IDonHang;
import vn.com.devmaster.service.managermaterial.spl.Sql;

import java.util.List;

@Repository
public interface OderRespon extends CrudRepository<Order,Integer> {
    @Query(value = Sql.DONHANG,nativeQuery = true)
    List<IDonHang> getDonHang(Integer id);
}
