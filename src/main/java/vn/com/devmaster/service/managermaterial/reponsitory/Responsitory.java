package vn.com.devmaster.service.managermaterial.reponsitory;

import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.managermaterial.domain.Category;
import vn.com.devmaster.service.managermaterial.domain.Customer;
import vn.com.devmaster.service.managermaterial.domain.Product;
import vn.com.devmaster.service.managermaterial.projecttion.*;
import vn.com.devmaster.service.managermaterial.spl.Sql;

import java.util.List;

@Repository
public interface Responsitory extends JpaRepository<Category, Integer> {

    @Query(value = "select  c.* from category c",nativeQuery = true)
    List<Category> getCategory();

    @Query(value = "select cus from Customer cus")
    List<Customer> getCustomer();

    @Query(value = Sql.PRODUCT,nativeQuery = true)
    List<IProduct> getProduct();

    @Query(value = Sql.PRODUCT_HP,nativeQuery = true)
    List<IProduct> getProductHp();

    @Query(value = Sql.PRODUCT_ASUS,nativeQuery = true)
    List<IProduct> getProductAsus();

    @Query(value = Sql.PRODUCT_DELL,nativeQuery = true)
    List<IProduct> getProductDell();

    @Query(value = Sql.PRODUCT_ACER,nativeQuery = true)
    List<IProduct> getProductAcer();

    @Query(value = Sql.PRODUCT_MACBOOK,nativeQuery = true)
    List<IProduct> getProductMac();

    @Query(value = Sql.PAYMENT_METHOD,nativeQuery = true)
    List<IPayment_method> getPaymentMethod();

    @Query(value = Sql.TRANSPORT_METHOD,nativeQuery = true)
    List<ITransport> getTransport();

//    @Query(value = Sql.PRODUCT_CHITIET,nativeQuery = true)
//    List<IProductChiTiet> getProductChiTiet();

    @Query(value = Sql.LOCPRICE_10,nativeQuery = true)
    List<IProduct> getLocPrice();

    @Query(value = Sql.LOCPRICE_10_15,nativeQuery = true)
    List<IProduct> getLocPrice1();

    @Query(value = Sql.LOCPRICE_15_20,nativeQuery = true)
    List<IProduct> getLocPrice2();

    @Query(value = Sql.LOCPRICE_20_25,nativeQuery = true)
    List<IProduct> getLocPrice3();

    @Query(value = Sql.LOCPRICE_30,nativeQuery = true)
    List<IProduct> getLocPrice4();
    @Query(value = Sql.LOCPRICE_25_30,nativeQuery = true)
    List<IProduct> getLocPrice5();

    @Query(value = Sql.PRODUCT_IMAGE,nativeQuery = true)
    List<IProductImage> getProductImage(Integer id);

    @Query(value = Sql.PRODUCT_TRANGCHU,nativeQuery = true)
    List<IProduct> getProductTC();

    @Query(value = Sql.ODERS,nativeQuery = true)
    List<IOders> getOders(Integer id2);

    @Query(value = Sql.THANH_TOAN,nativeQuery = true)
    List<IPayment_method> getPaymentActive();

    @Query(value = Sql.VAN_CHUYEN,nativeQuery = true)
    List<ITransport> getTransPort(Integer id);
    @Query(value = Sql.VAN_CHUYEN1,nativeQuery = true)
    ITransport getTransPort1(Integer id1);
    @Query(value = Sql.VAN_CHUYEN1,nativeQuery = true)
    ITransport getTransPort2(Integer id,Integer id1);

    @Query(value = Sql.PAYMENT,nativeQuery = true)
    IPayment_method getPayment(Integer id);


}
