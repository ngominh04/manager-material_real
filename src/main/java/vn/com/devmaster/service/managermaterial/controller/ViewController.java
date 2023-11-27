package vn.com.devmaster.service.managermaterial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.com.devmaster.service.managermaterial.domain.*;
import vn.com.devmaster.service.managermaterial.reponsitory.*;
import vn.com.devmaster.service.managermaterial.service.*;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/view")
public class ViewController {
    @Autowired
    CustomerRespon customerRespon;
    @Autowired
    ParamService paramService;
    @Autowired
    Responsitory responsitory;
    @Autowired
    SessionService sessionService;
    @Autowired
    ProductRespon productRespon;

    @Autowired
    Service service;

    @GetMapping("/products")
    String getProduct(Model model){
        model.addAttribute("prod",responsitory.getProduct());
        return "product";
    }
    @GetMapping("/products1")
    String getProductMinMax(Model model){
        model.addAttribute("prod",responsitory.getProductMinMax());
        return "product";
    }
    @GetMapping("/products2")
    String getProductMaxMin(Model model){
        model.addAttribute("prod",responsitory.getProductMaxMin());
        return "product";
    }

    @GetMapping("/hp")
    String showProductHp(Model model){
        model.addAttribute("hp",responsitory.getProductHp());
        return "product_hp";
    }

    @GetMapping("/asus")
    String showProductAsus(Model model){
        model.addAttribute("asus",responsitory.getProductAsus());
        return "product_asus";
    }

    @GetMapping("/dell")
    String showProductDell(Model model){
        model.addAttribute("dell",responsitory.getProductDell());
        return "product_dell";
    }

    @GetMapping("/acer")
    String showProductAcer(Model model){
        model.addAttribute("acer",responsitory.getProductAcer());
        return "product_acer";
    }

    @GetMapping("/mac")
    String showProductMac(Model model){
        model.addAttribute("mac",responsitory.getProductMac());
        return "product_mac";
    }

    // show toàn bộ những hình thức thanh toán
    @GetMapping("/payment_method")
    String showPaymentMethod(Model model){
        model.addAttribute("payment_method",responsitory.getPaymentMethod());
        return "paymentMethod";
    }

    // show toàn bộ phương thức giao hàng
    @GetMapping("/transport_method")
    String showTransportMethod(Model model){
        model.addAttribute("transport_method",responsitory.getTransport());
        return "transportMethod";
    }

    // show toàn bộ product
    @GetMapping("/productAll")
    String showAllProduct(Model model){
        model.addAttribute("productAll",responsitory.getProduct());
        return "productAll";
    }


    // lấy ra product id chi tiết
    @GetMapping("/productAll/{id}")
    String showProductChiTiets(Model model,@PathVariable(name = "id") Integer id){
        model.addAttribute("productId",productRespon.findAllById(id));
        model.addAttribute("productImage",responsitory.getProductImage(id));
        model.addAttribute("imageId",responsitory.getProductImage(id));
        return "productChiTiet";
    }

    // lọc dưới 10 tr
    @GetMapping("/locPrice_10")
    public String showLocPrice(Model model){
        model.addAttribute("locPrice_10",responsitory.getLocPrice());
        return "locTheoGia/locPrice";
    }

    // lọc 10 đến 15tr
    @GetMapping("/locPrice_10_15")
    public String showLocPrice1(Model model){
        model.addAttribute("locPrice_10_15",responsitory.getLocPrice1());
        return "locTheoGia/locPrice1";
    }

    // lọc 15 đến 20tr
    @GetMapping("/locPrice_15_20")
    public String showLocPrice2(Model model){
        model.addAttribute("locPrice_15_20",responsitory.getLocPrice2());
        return "locTheoGia/locPrice2";
    }

    @GetMapping("/locPrice_20_25")
    public String showLocPrice3(Model model){
        model.addAttribute("locPrice_20_25",responsitory.getLocPrice3());
        return "locTheoGia/locPrice3";
    }

    @GetMapping("/locPrice_30")
    public String showLocPrice4(Model model){
        model.addAttribute("locPrice_30",responsitory.getLocPrice4());
        return "locTheoGia/locPrice4";
    }

    @GetMapping("/locPrice_25_30")
    public String showLocPrice5(Model model){
        model.addAttribute("locPrice_25_30",responsitory.getLocPrice5());
        return "locTheoGia/locPrice5";
    }
    @GetMapping("/login")
    public String showLogin(){
        return "login";
    }

    @Autowired
    CartService cartService;

    @PostMapping("/login_check")
    public String login(Model  model, @RequestParam(name = "username") String username, HttpSession session,CartItem item){
//        String username = paramService.getString("username","");
        String psw = paramService.getString("psw","");
        Customer customer= customerRespon.getCustomer1(username);
        try {

            if(!customer.getPassword().equals(psw)){
                model.addAttribute("message","invalid password");

            }else {
                session.setAttribute("saveCus",customer);
                session.setAttribute("saveIdCustomer",customer.getId());
                session.setAttribute("saveNameCustomer",customer.getName());
                session.setAttribute("username",username);
                model.addAttribute("customer",customerRespon.getCustomer1(username));

                List<Nguoinhan> nguoinhan = nguoiNhanRespon.getNguoinhan(customer.getId());
                model.addAttribute("listNguoiNhan",nguoinhan);

                item.setUsername(username);
                if(customer.getPhanquyen() == 1){
                    return "admin/admin";
                }else {
                    return "login/login_check";
                }


            }
        }catch (Exception e){
            model.addAttribute("message","invalid user name");
        }

        return "login";
    }
    @Autowired
    CustomerService customerService;
    @GetMapping("/logout")
    public String logout(HttpSession session,Customer customer){
        session.removeAttribute("username");
        session.removeAttribute("saveCus");
        session.removeAttribute("saveIdCustomer");
        session.removeAttribute("saveNameCustomer");
        customerService.delete(customer);
        return "login/notification";
    }

    @Autowired
    CartItemRespon cartItemRespon;
    @Autowired
    PaymentRespon paymentRespon;
    @Autowired
    TransportRespon  transportRespon;
    @Autowired
    NguoiNhanRespon nguoiNhanRespon;


    @GetMapping("/showChiTiet/{IdCustomer}")
    public String showChiTiet(Model model, HttpSession session
            ,@PathVariable(name = "IdCustomer") Integer IdCustomer){
        session.getAttribute("saveCus");
        model.addAttribute("customer",customerRespon.getCustomerId(IdCustomer));
        session.getAttribute("saveProduct");
        // tổng tiền
        Collection<CartItem> item = cartItemRespon.getById(IdCustomer);
        double tongTien=0;
        for (CartItem item1: item) {
            tongTien = tongTien+(item1.getQuantity() * item1.getPrice());
        }
        model.addAttribute("tongTien",tongTien);

        model.addAttribute("cartItem",cartItemRespon.getById(IdCustomer));
        model.addAttribute("listPayment",paymentRespon.getPaymentMethod());

        model.addAttribute("listNguoiNhan",nguoiNhanRespon.getNguoinhan(IdCustomer));
        model.addAttribute("listTransport",transportRespon.getTransportMethod());

        return "layout/index1";
    }

    @GetMapping("/testOder")
    public String a(){
        return "oder/Oder";
    }

    // search
    @PostMapping("/search")
    public String search(Model model,@RequestParam(name = "name")String name){
        model.addAttribute("finALlByNamePro",productRespon.findAllByName(name));
        return "searchProduct";
    }
}
