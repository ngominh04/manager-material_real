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
import vn.com.devmaster.service.managermaterial.service.*;

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
    ProductService productService;
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
    public String showOder(@RequestParam(name = "id",required = false) Integer id,
                           @RequestParam(name = "idpayment",required = false) Integer idpayment,
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
            // cập nhật lại số lượng sản phẩm
            Product product = productRespon.findAllById(id);
            product.setQuatity(product.getQuatity()-item1.getQuantity());
            // cập nhật lại trạng thái nếu hết hàng
            if(product.getQuatity() == 0){
                product.setIsactive((byte) 0);
            }
            productRespon.save(product);
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



        model.addAttribute("order",order);
        model.addAttribute("ordersPayment",ordersPayment);
        model.addAttribute("ordersTransport",ordersTransport);
        model.addAttribute("cartItem",service.getAllItem());
//        model.addAttribute("")
        return "oder/Oder";
    }

    @PostMapping("/orderUser1")
    public String showOder1(@ModelAttribute("order") Order order){

        return "oder/Oder";
    }
}