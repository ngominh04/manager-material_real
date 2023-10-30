package vn.com.devmaster.service.managermaterial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.com.devmaster.service.managermaterial.domain.*;
import vn.com.devmaster.service.managermaterial.reponsitory.CustomerRespon;
import vn.com.devmaster.service.managermaterial.reponsitory.OderDetailRespon;
import vn.com.devmaster.service.managermaterial.reponsitory.OderRespon;
import vn.com.devmaster.service.managermaterial.service.OderService;
import vn.com.devmaster.service.managermaterial.service.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/oder")
public class OderController {
    @Autowired
    CustomerRespon customerRespon;
    @Autowired
    OderRespon oderRespon;
    @Autowired
    OderService oderService;
    @Autowired
    OderDetailRespon oderDetailRespon;
    @Autowired
    Service service;
    @GetMapping("/oderUser/{username}")
    public String showOder(Model model, HttpSession session, @PathVariable(name = "username") String username){

        Customer customer= customerRespon.getCustomer1(username);
        List<CartItem> cart= customer.getCartItems();
//        oderService.save(cart);
        Order order = new Order();

//        Customer customer= (Customer) session.getAttribute("saveCus");
        int id = UUID.randomUUID().toString().indexOf(2,10);
        String idOrder = UUID.randomUUID().toString().substring(0,10);
//        idOrder =
//        order.setIdcustomer();
//        order.setId(id);
        order.setTotalMoney(service.getAmount());
        order.setOrdersDate(new Date().toInstant());
        order.setIdorders(idOrder);
        List<OrdersDetail> orderDetailList = new ArrayList<>();
        for (CartItem item : cart) {
            OrdersDetail orderDetail = new OrdersDetail();
            orderDetail.setIdord(order);
            orderDetail.setIdproduct(item.getProduct());
            oderDetailRespon.save(orderDetail);
            orderDetailList.add(orderDetail);
        }
//        oderService.save((CartItem) cart);
        oderRespon.save(order);
        return "test";
    }

}
