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
import vn.com.devmaster.service.managermaterial.projecttion.IProduct;
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
    CartService cartService;
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
    @Autowired
    NguoiNhanRespon nguoiNhanRespon;
    @Autowired
    NguoiNhanService nguoiNhanService;
    @Autowired
    OrderNguoiNhanRespon orderNguoiNhanRespon;
    @Autowired
    OrderNguoiNhanService orderNguoiNhanService;

    @PostMapping("/oderUser/{username}")
    public String showOder(@RequestParam(name = "id",required = false) Integer id,
                           @RequestParam(name = "idnguoiNhan",required = false) Integer idnguoiNhan,
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
        order.setNotes("Có");
        order.setIdcustomer(customer);
        order.setTrangThai(1);

        // save nguonhan
        OrdersNguoinhan  ordersNguoinhan = new OrdersNguoinhan();
        Nguoinhan nguoinhan = nguoiNhanRespon.findAllById(idnguoiNhan);
        ordersNguoinhan.setIdguoiNhan(nguoinhan);
        ordersNguoinhan.setIdorder(order);
        orderNguoiNhanService.save(ordersNguoinhan);

        order.setAddress(ordersNguoinhan.getIdguoiNhan().getAddress());
        order.setNameReciver(ordersNguoinhan.getIdguoiNhan().getName());
        order.setPhone(ordersNguoinhan.getIdguoiNhan().getPhone());

        // save order  detail
        Collection<CartItem> item = cartItemRespon.findByUsername(username);
        List<OrdersDetail> orderDetailList = new ArrayList<>();
        for (CartItem item1 : item) {
            OrdersDetail orderDetail = new OrdersDetail();
            orderDetail.setIdord(order);
            orderDetail.setQty(item1.getQuantity());
            orderDetail.setIdproduct(item1.getIdProduct());
            orderDetail.setPrice(item1.getPrice());
            //save oder detail
            oderDetailRespon.save(orderDetail);
            orderDetailList.add(orderDetail);
            // cập nhật lại số lượng sản phẩm
            int idProduct =  item1.getIdProduct();
            Product product = productRespon.findAllById(idProduct);
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
        ordersTransport.setTotal(transportMethod.getPrice());
        ordersTransport.setIdord(order);
        oderTransportService.save(ordersTransport);

        session.removeAttribute("cartItem");
        session.setAttribute("saveOder",order);
        // tổng tiền
        double tongTien=0;
        for (CartItem item1: item) {
            tongTien = tongTien+(item1.getQuantity() * item1.getPrice());
        }
        order.setTotalMoney(tongTien+transportMethod.getPrice());
        session.removeAttribute("saveProduct");
        oderRespon.save(order);
        // xóa cart ra khỏi giỏ hàng
        cartService.deleteAll();

        model.addAttribute("order",order);
        model.addAttribute("ordersPayment",ordersPayment);
        model.addAttribute("ordersTransport",ordersTransport);
        model.addAttribute("cartItem",service.getAllItem());
//        model.addAttribute("")
        return "test";
    }

    @Autowired
    OderService oderService;
    @GetMapping("/donhang/{idCus}")
    public String showDonhangChiTiet1(@PathVariable(name = "idCus")Integer idCus,Model model){
        List<IProduct> product= responsitory.getProductTC();
        model.addAttribute("trangChu",product);
        return "oder/menu_order";
    }

    @GetMapping("/donhang1/{idCus}")
    public String showDonhang1(Model model,@PathVariable(name = "idCus") Integer idCus){
        model.addAttribute("donhang",oderRespon.getDonHang(idCus));
        model.addAttribute("trangThai1","Đơn hàng bạn đang chờ xác nhận");
        return "oder/Oder";
    }
    @GetMapping("/donhang2/{idCus}")
    public String showDonhang2(Model model,@PathVariable(name = "idCus") Integer idCus){
        model.addAttribute("donhang",oderRespon.getDonHang2(idCus));
        model.addAttribute("trangThai2","Đơn hàng đang được giao");
        return "oder/Oder";
    }
    @GetMapping("/donhang3/{idCus}")
    public String showDonhang3(Model model,@PathVariable(name = "idCus") Integer idCus){
        model.addAttribute("donhang",oderRespon.getDonHang3(idCus));
        model.addAttribute("trangThai3","Đơn hàng đã giao đến bạn");
        return "oder/Oder";
    }
    @GetMapping("/donhang0/{idCus}")
    public String showDonhang0(Model model,@PathVariable(name = "idCus") Integer idCus){
        model.addAttribute("donhang",oderRespon.getDonHang0(idCus));
        model.addAttribute("trangThai0","Đơn hàng bạn đã mua");
        return "oder/Oder";
    }
    @GetMapping("/donhang4/{idCus}")
    public String showDonhang4(Model model,@PathVariable(name = "idCus") Integer idCus){
        model.addAttribute("donhang",oderRespon.getDonHang4(idCus));
        model.addAttribute("trangThai4","Đơn hàng bạn đã hủy");
        return "oder/Oder";
    }

   @GetMapping("donhang_ChiTiet/{idCus}/{idOrder}/{idNguoiNhan}")
    public String showDonHangChiTiet(Model model,@PathVariable(name = "idCus") Integer idCus
           ,@PathVariable(name = "idOrder") Integer idOrder
           ,@PathVariable(name = "idNguoiNhan") Integer idNguoiNhan){
        model.addAttribute("donhang_product",oderDetailRespon.getOrdersDetailById(idOrder));
        model.addAttribute("donhang_ChiTiet",oderRespon.getDonHangChiTiet(idCus,idOrder,idNguoiNhan));
        return "/oder/orderChiTiet";
   }
//   @GetMapping("/back")
//    public String back(Model model){
//        return "redirect:/oder/menu_order";
//   }
   @GetMapping("/back/{idCus}")
    public String back(Model model,@PathVariable(name = "idCus")Integer idCus){
        return "redirect:/oder/donhang/{idCus}";
   }
   @GetMapping("/xacNhan/{idOrder}")
    public String xacNhan(Model model,@PathVariable(name = "idOrder")Integer idOrder){
        Order order = oderRespon.findAllById(idOrder);
        order.setTrangThai(0);
        oderRespon.save(order);
        model.addAttribute("trangthai0","Bạn đã nhận được hàng hàng thành công");
        return "oder/notify";
   }
    @GetMapping("/huyDon/{idOrder}")
    public String huyDon(Model model,@PathVariable(name = "idOrder")Integer idOrder){
        Order order = oderRespon.findAllById(idOrder);
        order.setTrangThai(4);
        oderRespon.save(order);
        model.addAttribute("trangthai4","Bạn đã hủy đơn hàng ");
        return "oder/notify";
    }
    @GetMapping("/muaLai/{idOrder}")
    public String muaLai(Model model,@PathVariable(name = "idOrder")Integer idOrder){
        Order order = oderRespon.findAllById(idOrder);
        order.setTrangThai(1);
        oderRespon.save(order);
        model.addAttribute("trangthai1","Bạn đã mua lại đơn hàng thành công");
        return "oder/notify";
    }
   // ở admin
   @GetMapping("/donhang1_admin")
   public String showDonhang1Adin(Model model){
       model.addAttribute("donhang",oderRespon.getDonHangAdmin());
       return "admin/orderAdmin";
   }
    @GetMapping("/xacNhanDonAdmin/{idOrder}")
    public String xacNhanDonAdmin(Model model,@PathVariable(name = "idOrder")Integer idOrder){
        Order order = oderRespon.findAllById(idOrder);
        order.setTrangThai(2);
        oderRespon.save(order);
        return "redirect:/oder/donhang1_admin";
    }
    @GetMapping("/donhang2_admin")
    public String showDonhang2Adin(Model model){
        model.addAttribute("donhang",oderRespon.getDonHang2Admin());
        return "admin/orderAdmin";
    }
    @GetMapping("/xacNhanDon1Admin/{idOrder}")
    public String xacNhanDonAdmin1(Model model,@PathVariable(name = "idOrder")Integer idOrder){
        Order order = oderRespon.findAllById(idOrder);
        order.setTrangThai(3);
        oderRespon.save(order);
        return "redirect:/oder/donhang1_admin";
    }
}