package vn.com.devmaster.service.managermaterial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.com.devmaster.service.managermaterial.domain.Customer;
import vn.com.devmaster.service.managermaterial.domain.PaymentMethod;
import vn.com.devmaster.service.managermaterial.reponsitory.PaymentRespon;
import vn.com.devmaster.service.managermaterial.reponsitory.Responsitory;
import vn.com.devmaster.service.managermaterial.service.PaymentService;

@Controller
@RequestMapping("/method")
public class MethodController {
    @Autowired
    PaymentService paymentService;
    @Autowired
    PaymentRespon paymentRespon;
    @Autowired
    Responsitory responsitory;
    @GetMapping("/showAllPayment")
    public String showAllCustomer(Model model){
        model.addAttribute("showAll",paymentRespon.getPayment());
        return "/method/showMethod";
    }
    @GetMapping("/payment")
    public String showForm(Model model){
        model.addAttribute("payment",new PaymentMethod());
        return "/login/register";
    }

}
