package vn.com.devmaster.service.managermaterial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.com.devmaster.service.managermaterial.domain.CartItem;
import vn.com.devmaster.service.managermaterial.domain.Customer;
import vn.com.devmaster.service.managermaterial.domain.Product;
import vn.com.devmaster.service.managermaterial.reponsitory.CustomerRespon;
import vn.com.devmaster.service.managermaterial.reponsitory.ProductRespon;
import vn.com.devmaster.service.managermaterial.reponsitory.Responsitory;
import vn.com.devmaster.service.managermaterial.service.ParamService;
import vn.com.devmaster.service.managermaterial.service.Service;

import javax.servlet.http.HttpSession;

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
    // giỏ hàng
    @GetMapping("/a")
    public String showCart(Model model, HttpSession session){
        session.getAttribute("saveCus");
        model.addAttribute("cartItem",service.getAllItem());
        model.addAttribute("tongTien",service.getAmount()); // xử lí tổng tiền sản phẩm
        return "cart/shopingcart";
    }
    // add sản phẩm vào giỏ hàng
    @GetMapping("/add/{id}")
    public String addCart(@PathVariable(name = "id" ) Integer id){
        Product product = productRespon.findAllById(id);
        if(product != null){
            CartItem item = new CartItem();
            item.setId(product.getId());
            item.setImage(product.getImage());
            item.setName(product.getName());
            item.setPrice(product.getPrice());
            item.setQuantity(1);
            service.add(item);
        }
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
//    @PostMapping("update")
//    public String update(){
//        Integer id = paramService.getInt("id",0);
//        int quantity = paramService.getInt("quantity",1);
//        return "redirect:/shoping_cart/a";
//    }


}
