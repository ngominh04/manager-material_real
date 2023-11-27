package vn.com.devmaster.service.managermaterial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.com.devmaster.service.managermaterial.domain.Customer;
import vn.com.devmaster.service.managermaterial.domain.Nguoinhan;
import vn.com.devmaster.service.managermaterial.reponsitory.CustomerRespon;
import vn.com.devmaster.service.managermaterial.service.CustomerService;
import vn.com.devmaster.service.managermaterial.service.ParamService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;
import java.util.List;
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

    // người dunng quên password
    @GetMapping("/check")
    public String check(){
        return "/login/check_email";
    }
    @Autowired
    ParamService  paramService;
    // check email ng dùng
    @PostMapping("/checkEmail")
    public String checkEmail(Model model, HttpSession session){
        String email = paramService.getString("email","");
        Customer customer= customerRespon.getEmailCustomer(email);


        try {
            if(!customer.getEmail().equals(email)){
                model.addAttribute("message","Sai email");

            }else {session.setAttribute("saveEmail",customer.getEmail());
                model.addAttribute("customer",customerRespon.getEmailCustomer(email));
                return "/login/change_password";
            }
        }catch (Exception e){
            model.addAttribute("message","Sai email");
        }
        return "/login/check_email";
    }
    // thay đổi password người dùng
    @PostMapping("/changePassword/{email}")
    public String changePassword(@Validated @ModelAttribute(name = "customer") Customer customer,BindingResult result
            ,Model model,@PathVariable(name = "email") String email,HttpSession session){
        Customer customer1=customerRespon.getEmailCustomer(email);

        model.addAttribute("customer1",customer1);
        model.addAttribute("customer",customer);
        customer1.setPassword(customer.getPassword());
        customerService.save(customer1);
        session.removeAttribute("saveEmail");
        return "/login/notify_email";
    }
}
