package vn.com.devmaster.service.managermaterial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.com.devmaster.service.managermaterial.domain.Customer;
import vn.com.devmaster.service.managermaterial.domain.PaymentMethod;
import vn.com.devmaster.service.managermaterial.reponsitory.PaymentRespon;
import vn.com.devmaster.service.managermaterial.reponsitory.Responsitory;
import vn.com.devmaster.service.managermaterial.service.PaymentService;

import java.util.Optional;

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
        return "/method/method";
    }
    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate(@Validated @ModelAttribute(name = "payment") PaymentMethod paymentMethod,
                               BindingResult result,
                               Model model){
        if(result.hasErrors()){
            model.addAttribute("message","Có thông tin bạn chưa nhập");
            return "/method/method";
        }
        paymentService.save(paymentMethod);
        model.addAttribute("payment",new PaymentMethod());

        return "redirect:/method/showAllPayment";
    }
    @PostMapping("/saveOrUpdate/{id}")
    public String saveOrUpdate(@Validated @ModelAttribute(name = "payment") PaymentMethod paymentMethod,
                               BindingResult result,
                               Model model,
                               @PathVariable(name = "id") Integer id){

        // cập nhật lên payment để sửa
        Optional<PaymentMethod> paymentMethod1= paymentService.findById(id);
        if(paymentMethod1.isPresent()){
            model.addAttribute("paymentMethod1",paymentMethod1.get());
            paymentService.save(paymentMethod);
            return "redirect:/method/showAllPayment";
        }else {
            model.addAttribute("payment",new PaymentMethod());
        }
        return "redirect:/method/showAllPayment";
    }

    @GetMapping("/payment/{id}")
    public String edit(@PathVariable(name = "id") Integer id,Model model){
        Optional<PaymentMethod> paymentMethod1= paymentService.findById(id);
        if(paymentMethod1.isPresent()){
            model.addAttribute("paymentMethod1",paymentMethod1.get());
        }else {
            model.addAttribute("payment",new PaymentMethod());
        }
        return "/method/method";
    }
    @GetMapping("/removePayment/{id}")
    public String remove(@PathVariable(name = "id")Integer id){
        paymentService.deleteById(id);
        return "redirect:/method/showAllPayment";
    }
}
