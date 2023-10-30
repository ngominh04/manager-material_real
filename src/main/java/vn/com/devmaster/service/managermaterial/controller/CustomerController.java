package vn.com.devmaster.service.managermaterial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.com.devmaster.service.managermaterial.domain.Customer;
import vn.com.devmaster.service.managermaterial.reponsitory.CustomerRespon;
import vn.com.devmaster.service.managermaterial.service.CustomerService;

import java.util.Optional;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerRespon customerRespon;
    @Autowired
    CustomerService customerService;
    @GetMapping("/register")
    public String showForm(Model model){
        model.addAttribute("customer",new Customer());
        return "/login/register";
    }

    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate(@Validated @ModelAttribute(name = "customer") Customer customer,
                               BindingResult result,
                               Model model){
        if(result.hasErrors()){
            model.addAttribute("message","Bạn chưa nhập");
            return "/login/register";
        }
        customerService.save(customer);
        model.addAttribute("customer",new Customer());
        return "/login/register";
    }
    @GetMapping("/showAllCustomer")
    public String showAllCustomer(Model model){
        model.addAttribute("showAll",customerRespon.getCustomer());
        return "/login/showRegister";
    }
//    @GetMapping("/show")
//    public String showCustomer(Model model){
//        model.addAttribute("showCus",customerService.findAll());
//        return "/login/showRegister";
//    }

    @GetMapping("/register/{id}")
    public String edit(@PathVariable(name = "id") Integer id,Model model){
        Optional<Customer> customer= customerService.findById(id);
        if(customer.isPresent()){
            model.addAttribute("customer",customer.get());
        }else {
            model.addAttribute("customer",new Customer());
        }
        return "/login/register";
    }
}
