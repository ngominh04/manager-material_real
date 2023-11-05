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

import javax.persistence.criteria.CriteriaBuilder;
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
            model.addAttribute("message","Có thông tin bạn chưa nhập");
            return "/login/register";
        }
        customerService.save(customer);
        model.addAttribute("customer",new Customer());

        return "/login/notification";
    }

    @PostMapping("/saveOrUpdate/{id}")
    public String saveOrUpdate(@Validated @ModelAttribute(name = "customer") Customer customer,
                               BindingResult result,
                               Model model,
                               @PathVariable(name = "id") Integer id){
        // cập nhật lên register để sửa
        Optional<Customer> customer1= customerService.findById(id);
        if(customer1.isPresent()){
            model.addAttribute("customer1",customer1.get());
            customerService.save(customer);
            return "redirect:/customer/showAllCustomer";
        }else {
            model.addAttribute("customer",new Customer());
        }
        return "/login/notification";
    }
    @GetMapping("/showAllCustomer")
    public String showAllCustomer(Model model){
        model.addAttribute("showAll",customerRespon.getCustomer());
        return "/login/showRegister";
    }

    @GetMapping("/register/{id}")
    public String edit(@PathVariable(name = "id") Integer id,Model model){
        Optional<Customer> customer= customerService.findById(id);
        if(customer.isPresent()){
            model.addAttribute("customer1",customer.get());
        }else {
            model.addAttribute("customer",new Customer());
        }
//        customerService.deleteById(id);
        return "/login/register";
    }
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable(name = "id")Integer id){
        customerService.deleteById(id);
        return "redirect:/customer/showAllCustomer";
    }
    @GetMapping("/admin")
    public String admin(Model model){
        return "admin/admin";
    }
    @GetMapping("/back")
    public String back(){
        return "redirect:/customer/admin";
    }
}
