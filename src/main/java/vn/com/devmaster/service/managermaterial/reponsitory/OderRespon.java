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
    Order findAllById(Integer idOrder);
    @Query(value = Sql.DONHANG_ADMIN,nativeQuery = true)
    List<IDonHang> getDonHangAdmin();
    @Query(value = Sql.DONHANG2_ADMIN,nativeQuery = true)
    List<IDonHang> getDonHang2Admin();
    @Query(value = Sql.DONHANG_TH1,nativeQuery = true)
    List<IDonHang> getDonHang(Integer idCus);
    @Query(value = Sql.DONHANG_TH2,nativeQuery = true)
    List<IDonHang> getDonHang2(Integer idCus);
    @Query(value = Sql.DONHANG_TH3,nativeQuery = true)
    List<IDonHang> getDonHang3(Integer idCus);
    @Query(value = Sql.DONHANG_TH4,nativeQuery = true)
    List<IDonHang> getDonHang4(Integer idCus);

    @Query(value = Sql.DONHANG_TH0,nativeQuery = true)
    List<IDonHang> getDonHang0(Integer idCus);

    @Query(value = Sql.ADMIN_DON_HANG_DA_MUA,nativeQuery = true)
    List<IDonHang> getDonHangDaMua();

    @Query(value = Sql.DONHANG1,nativeQuery = true)
    IDonHang getDonHangChiTiet(Integer idCus,Integer idOrder,Integer idNguoiNhan);

}
