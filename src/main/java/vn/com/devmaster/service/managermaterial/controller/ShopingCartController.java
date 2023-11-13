package vn.com.devmaster.service.managermaterial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.com.devmaster.service.managermaterial.domain.CartItem;
import vn.com.devmaster.service.managermaterial.domain.Customer;
import vn.com.devmaster.service.managermaterial.domain.Nguoinhan;
import vn.com.devmaster.service.managermaterial.domain.Product;
import vn.com.devmaster.service.managermaterial.reponsitory.*;
import vn.com.devmaster.service.managermaterial.service.CartService;
import vn.com.devmaster.service.managermaterial.service.ParamService;
import vn.com.devmaster.service.managermaterial.service.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

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

    // giỏ hàng khi chưa đăng nhập
    @GetMapping("/a")
    public String showCart(Model model, HttpSession session){
        session.setAttribute("saveCart",service.getAllItem());
        session.getAttribute("saveCart");
        session.getAttribute("saveCus");
        session.getAttribute("saveProduct");
        model.addAttribute("cartItem",service.getAllItem());
        session.getAttribute("tranport");
        model.addAttribute("tongTien_noUser",service.getAmount());
        return "cart/shopingcart";
    }
    @GetMapping("/a/{username}")
    public String showCart(Model model, HttpSession session,@PathVariable(name = "username")String username){
        session.setAttribute("saveCart",service.getAllItem());
        session.getAttribute("saveCart");
        session.getAttribute("saveCus");
        session.getAttribute("saveProduct");
//        model.addAttribute("cartItem",service.getAllItem());
        List<CartItem> item = cartItemRespon.findByUsername(username);
        // xử lí tổng tiền sản phẩm
        double tongTien=0;
        for (CartItem item1: item) {
            tongTien = tongTien+(item1.getQuantity() * item1.getPrice());
        }
        model.addAttribute("tongTien",tongTien);
        model.addAttribute("cartItem",item);
        session.getAttribute("tranport");
        session.setAttribute("tongTien",service.getAmount());
//        model.addAttribute("tongTien",service.getAmount()); // xử lí tổng tiền sản phẩm
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
//            item.setProduct(product);
            service.add(item);
        }
//        session.
        session.setAttribute("saveAllCart",service.getAllItem());

        return "redirect:/shoping_cart/a";
    }
    @Autowired
    NguoiNhanRespon nguoiNhanRespon;
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

        model.addAttribute("customer",customer);
        if(product != null){
            CartItem item = new CartItem();
//            item.setId(product.getId());
            item.setImage(product.getImage());
            item.setName(product.getName());
            item.setPrice(product.getPrice());
            item.setIdCustomer(customer.getId());
            item.setUsername(customer.getUsername());
            item.setQuantity(1);
            item.setIdProduct(product.getId());
            service.add(item);
            cartService.save(item);
        }
        session.setAttribute("saveAllCart",service.getAllItem());
//        session.setAttribute("saveItem",);
        return "redirect:/shoping_cart/a/{username}";
    }
    // xóa 1 sản phẩm có trong giỏ hàng theo id (xóa khi người dùng ko đăng nhập)
    @GetMapping("/remove/{id}")
    public String removeCart(@PathVariable(name = "id") Integer id){
        service.remove(id);
        return "redirect:/shoping_cart/a";
    }
    @GetMapping("/remove1/{idItem}/{username}")
    public String remove(@PathVariable(name = "idItem") Integer idItem,@PathVariable(name = "username")String username){
//        service.remove(idPro);
        cartService.deleteById(idItem);
        return "redirect:/shoping_cart/a/{username}";
    }

    // update quantity khi chưa đăng nhập
    @PostMapping("update")
    public String update(@RequestParam(name = "id") Integer id,@RequestParam(name = "quantity")int quantity){
        service.update(id,quantity);
        return "redirect:/shoping_cart/a";
    }
    @PostMapping("update1/{username}")
    public String update1(@RequestParam(name = "id") Integer id,@RequestParam(name = "quantity")int quantity,@RequestParam(name = "username")String username){
        CartItem item = cartItemRespon.findAllById(id);
        item.setQuantity(quantity);
        cartItemRespon.save(item);
        return "redirect:/shoping_cart/a/{username}";
    }
}
