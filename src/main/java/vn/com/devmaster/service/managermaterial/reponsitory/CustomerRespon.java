package vn.com.devmaster.service.managermaterial.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.managermaterial.domain.Customer;
import vn.com.devmaster.service.managermaterial.projecttion.ICustomer;
import vn.com.devmaster.service.managermaterial.projecttion.Icustomer_ChiTIet;

import java.util.List;

@Repository
public interface CustomerRespon extends JpaRepository<Customer, Integer> {
    @Query(value = "select c.* from Customer c",nativeQuery = true)
    List<Customer> getCustomer();
    @Query(value = "select c.* from Customer c where USERNAME = ?",nativeQuery = true)
    Customer getCustomer1(String  uname);

    @Query(value = "" +
            "select distinct c.USERNAME username,c.PASSWORD pwd,c.NAME tenDangNhap,c.ADDRESS address,c.EMAIL email,c.PHONE phone" +
            ",p.NAME sanPham,p.PRICE tienSanPham,od.QTY soLuong,tm.NAME vanChuyen,ot.TOTAL tienVanChuyen,pm.NAME thanhToan,o.TOTAL_MONEY tongTien\n" +
            "from customer c\n" +
            "    left join  orders o on c.ID = o.IDCUSTOMER\n" +
            "    left join orders_details od on o.ID = od.IDORD\n" +
            "    left join `manager-material`.product p on od.IDPRODUCT = p.ID\n" +
            "    left join orders_transport ot on o.ID = ot.IDORD\n" +
            "    left join orders_payment op on o.ID = op.IDORD\n" +
            "    left join transport_method tm on ot.IDTRANSPORT = tm.ID\n" +
            "    left join payment_method pm on op.IDPAYMENT = pm.ID\n" +
            "where c.ISACTIVE =1 and pm.ISACTIVE=1  and  c.USERNAME =?",nativeQuery = true)
    List<Icustomer_ChiTIet> getCustomer2(String uname);
}
