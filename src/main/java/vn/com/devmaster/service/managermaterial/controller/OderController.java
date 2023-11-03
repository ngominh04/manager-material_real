package vn.com.devmaster.service.managermaterial.controller;

import lombok.experimental.Tolerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.com.devmaster.service.managermaterial.domain.*;
import vn.com.devmaster.service.managermaterial.projecttion.IPayment_method;
import vn.com.devmaster.service.managermaterial.projecttion.Icustomer_ChiTIet;
import vn.com.devmaster.service.managermaterial.reponsitory.*;
import vn.com.devmaster.service.managermaterial.service.OderService;
import vn.com.devmaster.service.managermaterial.service.OrderPaymentService;
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
    OrderPaymentService orderPaymentService;
    @Autowired
    OrderPaymentRespon orderPaymentRespon;

    @GetMapping("/oderUser/{username}")
    public String showOder( HttpSession session, @PathVariable(name = "username") String username, Model model){

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
//        // save oder payment
//        Collection<PaymentMethod> paymentMethod = (Collection<PaymentMethod>) session.getAttribute("payment");
        OrdersPayment ordersPayment = new OrdersPayment();
        model.addAttribute("payment",ordersPayment);
        ordersPayment.setTotal(0);
        ordersPayment.setNotes(1);
        ordersPayment.setIdord(order);
        ordersPayment.setStatus(1);
////        ordersPayment.setIdpayment();

//        orderPaymentService.save(ordersPayment);
        session.removeAttribute("cartItem");
        session.setAttribute("saveOder",order);
        oderRespon.save(order);
        return "test";
    }
    @PostMapping("/savePm_Tp")
    public String savePaymentTransport(@ModelAttribute(name = "payment") OrdersPayment payment,
                                       @ModelAttribute(name = "transport1") OrdersTransport transport,Model model){
//        Order order = (Order) session.getAttribute("saveOder");
        model.addAttribute("payment1",new OrdersPayment());
//        payment.setStatus(1);
//        payment.setTotal(0);
//        payment.setIdord(order);
//        payment.setNotes(1);
        orderPaymentService.save(payment);
        return "layout/index1";
    }
}