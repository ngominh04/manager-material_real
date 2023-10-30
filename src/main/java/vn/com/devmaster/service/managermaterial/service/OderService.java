package vn.com.devmaster.service.managermaterial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.devmaster.service.managermaterial.domain.*;
import vn.com.devmaster.service.managermaterial.reponsitory.CustomerRespon;
import vn.com.devmaster.service.managermaterial.reponsitory.OderRespon;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.UUID;

@Service
public class OderService {
    @Autowired
    OderRespon oderRespon;
    @Autowired
    CustomerRespon customerRespon;

    @Autowired
    HttpSession session;

    @Transactional
    public Order save(CartItem item){
        Order order = new Order();
//        Customer customer= (Customer) session.getAttribute("saveCus");
        String idOrder = UUID.randomUUID().toString().substring(0,10);
//        idOrder =
//        order.setIdcustomer();
        order.setTotalMoney(item.getPrice()* item.getQuantity());
        order.setOrdersDate(new Date().toInstant());
        order.setIdorders(idOrder);
        return oderRespon.save(order);
    }
}
