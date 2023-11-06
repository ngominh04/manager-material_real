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
import vn.com.devmaster.service.managermaterial.service.OderTransportService;
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
    @Autowired
    PaymentRespon paymentRespon;
    @Autowired
    TransportRespon transportRespon;
    @Autowired
    OderTransportService oderTransportService;

    @PostMapping("/oderUser/{username}")
    public String showOder(@RequestParam(name = "idpayment",required = false) Integer idpayment,
                           @RequestParam(name = "idtransport",required = false) Integer idtransport,
                           HttpSession session,
                           @PathVariable(name = "username") String username,
                           Model model){

//        session.getAttribute("username");
        Customer customer= customerRespon.getCustomer1(username);
        Order order = new Order();
        String idOrder = UUID.randomUUID().toString().substring(0,10);

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
        // save oder payment
        OrdersPayment ordersPayment = new OrdersPayment();
        PaymentMethod paymentMethod = paymentRespon.findAllById(idpayment);
        ordersPayment.setIdpayment(paymentMethod);
        ordersPayment.setIdord(order);
        orderPaymentService.save(ordersPayment);

        // save transport
        OrdersTransport ordersTransport = new OrdersTransport();
        TransportMethod transportMethod = transportRespon.findAllById(idtransport);
        ordersTransport.setIdtransport(transportMethod);
        ordersTransport.setIdord(order);
        oderTransportService.save(ordersTransport,idtransport);


        session.removeAttribute("cartItem");
        session.setAttribute("saveOder",order);
        // tổng tiền
        Double amount =service.getAmount();
        Double total =amount+ordersTransport.getTotal();
        order.setTotalMoney(total);
        model.addAttribute("tongTien",total);
        session.removeAttribute("saveProduct");
        oderRespon.save(order);
        return "test";
    }
//    @GetMapping("/total")
//    public String total(@RequestParam(name = "idtransport",required = false) Integer idtransport,Model model){
//        TransportMethod transportMethod=transportRespon.findAllById(idtransport);
//        if(transportMethod.getId() == 1){
//            model.addAttribute("tongTien",service.getAmount()+50000);
//        }
//        if(transportMethod.getId() == 3){
//            model.addAttribute("tongTien",service.getAmount()+150000);
//        }
//        if(transportMethod.getId() == 5){
//            model.addAttribute("tongTien",service.getAmount()+250000);
//        }
//        else {
//            model.addAttribute("tongTien",service.getAmount()+10000);
//        }
//        return "/layout/index1";
//    }
}