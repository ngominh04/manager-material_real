package vn.com.devmaster.service.managermaterial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.com.devmaster.service.managermaterial.domain.CartItem;
import vn.com.devmaster.service.managermaterial.domain.Customer;
import vn.com.devmaster.service.managermaterial.domain.Product;
import vn.com.devmaster.service.managermaterial.reponsitory.CartItemRespon;
import vn.com.devmaster.service.managermaterial.reponsitory.CustomerRespon;
import vn.com.devmaster.service.managermaterial.reponsitory.ProductRespon;
import vn.com.devmaster.service.managermaterial.reponsitory.Responsitory;
import vn.com.devmaster.service.managermaterial.service.CartService;
import vn.com.devmaster.service.managermaterial.service.ParamService;
import vn.com.devmaster.service.managermaterial.service.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/shoping_cart")
public class ShopingCartController {
    @Autowired
    CustomerRespon customerRespon;
    @Autowired
    ParamService paramService;
    @Autowired
    Service service;
    @Autowired
    Responsitory responsitory;
    @Autowired
    ProductRespon productRespon;
    @Autowired
    CartItemRespon cartItemRespon;
    @Autowired
    CartService cartService;

    // giỏ hàng
    @GetMapping("/a")
    public String showCart(Model model, HttpSession session){
        session.setAttribute("saveCart",service.getAllItem());
        session.getAttribute("saveCart");
        session.getAttribute("saveCus");
        session.getAttribute("saveProduct");
        model.addAttribute("cartItem",service.getAllItem());
        session.getAttribute("tranport");
        session.setAttribute("tongTien",service.getAmount());
        model.addAttribute("tongTien",service.getAmount()); // xử lí tổng tiền sản phẩm
        return "cart/shopingcart";
    }
    // add sản phẩm vào giỏ hàng
    @GetMapping("/add/{id}")
    public String addCart(Model model,@PathVariable(name = "id" ) Integer id,HttpSession session){
        session.setAttribute("productId",productRespon.findAllById(id));
        session.getAttribute("productId");
        session.setAttribute("payment",responsitory.getPaymentActive());
        session.getAttribute("payment");
        session.setAttribute("tranport",responsitory.getTransPort());
        session.setAttribute("paymentId",responsitory.getPayment(id));
        Product product = productRespon.findAllById(id);

        if(product != null){
            CartItem item = new CartItem();
            item.setId(product.getId());
            item.setImage(product.getImage());
            item.setName(product.getName());
            item.setPrice(product.getPrice());
            item.setQuantity(1);
            item.setProduct(product);
            service.add(item);
        }
        session.setAttribute("saveAllCart",service.getAllItem());
        return "redirect:/shoping_cart/a";
    }
    @GetMapping("/add/{id}/{username}")
    public String addCart1(@PathVariable(name = "id" ) Integer id,@PathVariable(name = "username") String username,HttpSession session,Model model){
        session.setAttribute("productId",productRespon.findAllById(id));
        session.getAttribute("productId");
        session.setAttribute("payment",responsitory.getPaymentActive());
        session.getAttribute("payment");
        session.setAttribute("tranport",responsitory.getTransPort());
        session.getAttribute("tranport");
        session.setAttribute("paymentId",responsitory.getPayment(id));
//        Product product = productRespon.findAllById(id);
        Product product = productRespon.findAllById(id);
        model.addAttribute("productId",productRespon.findAllById(id));
        Customer customer = customerRespon.getCustomer1(username);
        if(product != null){
            CartItem item = new CartItem();
            item.setId(product.getId());
            item.setImage(product.getImage());
            item.setName(product.getName());
            item.setPrice(product.getPrice());
            item.setCustomer(customer);
//            item.setUsername(customer.getUsername());
            item.setQuantity(1);
            item.setProduct(product);
            service.add(item);
//            cartItemRespon.save(item);
        }
        session.setAttribute("saveAllCart",service.getAllItem());
//        session.setAttribute("saveItem",);
        return "redirect:/shoping_cart/a";
    }
    // xóa 1 sản phẩm có trong giỏ hàng theo id
    @GetMapping("/remove/{id}")
    public String removeCart(@PathVariable(name = "id") Integer id){
        service.remove(id);
        return "redirect:/shoping_cart/a";
    }

    // update quantity
    @PostMapping("update")
    public String update(@RequestParam(name = "id") Integer id,@RequestParam(name = "quantity")int quantity){
        service.update(id,quantity);
        return "redirect:/shoping_cart/a";
    }


}
