package vn.com.devmaster.service.managermaterial.controller;

import lombok.experimental.Tolerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.com.devmaster.service.managermaterial.domain.*;
import vn.com.devmaster.service.managermaterial.projecttion.IPayment_method;
import vn.com.devmaster.service.managermaterial.projecttion.Icustomer_ChiTIet;
import vn.com.devmaster.service.managermaterial.reponsitory.*;
import vn.com.devmaster.service.managermaterial.service.OderService;
import vn.com.devmaster.service.managermaterial.service.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/oder")
public class OderController {
    @Autowired
    CustomerRespon customerRespon;
    @Autowired
    OderRespon oderRespon;
    @Autowired
    Responsitory responsitory;
    @Autowired
    OderDetailRespon oderDetailRespon;
    @Autowired
    Service service;
    @Autowired
    ProductRespon productRespon;
    @Autowired
    CartItemRespon cartItemRespon;
    @Autowired
    OrderPaymentRespon orderPaymentRespon;

    @GetMapping("/oderUser/{username}")
    public String showOder( HttpSession session, @PathVariable(name = "username") String username,Model model){

        Customer customer= customerRespon.getCustomer1(username);
        Order order = new Order();
        String idOrder = UUID.randomUUID().toString().substring(0,10);
        order.setTotalMoney(service.getAmount());
        order.setOrdersDate(new Date().toInstant());
        order.setIdorders(idOrder);
        order.setAddress(customer.getAddress());
        order.setNameReciver(customer.getName());
        order.setPhone(customer.getPhone());
        order.setNotes("Có");
        order.setIdcustomer(customer);
        //save order payment
        PaymentMethod paymentMethod = (PaymentMethod) session.getAttribute("payment");
        OrdersPayment ordersPayment = new OrdersPayment();
        ordersPayment.setIdord(order);
        ordersPayment.setTotal(500000);
        ordersPayment.setNotes(1);
        ordersPayment.setStatus(1);
        ordersPayment.setIdpayment(paymentMethod);

        orderPaymentRespon.save(ordersPayment);

        Collection<CartItem> item = (Collection<CartItem>) session.getAttribute("saveAllCart");
        List<OrdersDetail> orderDetailList = new ArrayList<>();
        for (CartItem item1 : item) {
            OrdersDetail orderDetail = new OrdersDetail();
            orderDetail.setIdord(order);
            orderDetail.setQty(item1.getQuantity());
            orderDetail.setIdproduct(item1.getProduct());
            orderDetail.setPrice(item1.getPrice());
            //save oder detail
            oderDetailRespon.save(orderDetail);
            orderDetailList.add(orderDetail);
        }

        session.removeAttribute("cartItem");
        oderRespon.save(order);
        return "test";
    }
}