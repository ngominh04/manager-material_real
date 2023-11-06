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
import vn.com.devmaster.service.managermaterial.domain.TransportMethod;
import vn.com.devmaster.service.managermaterial.reponsitory.PaymentRespon;
import vn.com.devmaster.service.managermaterial.reponsitory.Responsitory;
import vn.com.devmaster.service.managermaterial.reponsitory.TransportRespon;
import vn.com.devmaster.service.managermaterial.service.PaymentService;
import vn.com.devmaster.service.managermaterial.service.TransportService;

import java.util.Optional;

@Controller
@RequestMapping("/method")
public class MethodController {
    @Autowired
    PaymentService paymentService;
    @Autowired
    PaymentRespon paymentRespon;
    @Autowired
    TransportRespon transportRespon;
    @Autowired
    TransportService transportService;
    @Autowired
    Responsitory responsitory;
    @GetMapping("/showAllPayment")
    public String showAllPayment(Model model){
        model.addAttribute("showAll",paymentRespon.getPayment());
        return "/method/showMethod";
    }
    @GetMapping("/showAllTransport")
    public String showAllTransport(Model model){
        model.addAttribute("showAll",transportRespon.getTransport());
        return "/method/showMethod1";
    }
    @GetMapping("/payment")
    public String showForm(Model model){
        model.addAttribute("payment",new PaymentMethod());
        return "/method/method";
    }
    @GetMapping("/transport")
    public String showForm1(Model model){
        model.addAttribute("transport",new TransportMethod());
        return "/method/method1";
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
    @PostMapping("/saveOrUpdate1")
    public String saveOrUpdate1(@Validated @ModelAttribute(name = "transport") TransportMethod transportMethod,
                               BindingResult result,
                               Model model){
        if(result.hasErrors()){
            model.addAttribute("message","Có thông tin bạn chưa nhập");
            return "/method/method";
        }
        transportService.save(transportMethod);
        model.addAttribute("transport",new TransportMethod());

        return "redirect:/method/showAllTransport";
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
    @PostMapping("/saveOrUpdate1/{id}")
    public String saveOrUpdate1(@Validated @ModelAttribute(name = "transport") TransportMethod transportMethod,
                               BindingResult result,
                               Model model,
                               @PathVariable(name = "id") Integer id){

        // cập nhật lên payment để sửa
        Optional<TransportMethod> transportMethod1= transportService.findById(id);
        if(transportMethod1.isPresent()){
            model.addAttribute("transportMethod1",transportMethod1.get());
            transportService.save(transportMethod);
            return "redirect:/method/showAllTransport";
        }else {
            model.addAttribute("transport",new TransportMethod());
        }
        return "redirect:/method/showAllTransport";
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
    @GetMapping("/transport/{id}")
    public String edit1(@PathVariable(name = "id") Integer id,Model model){
        Optional<TransportMethod> transportMethod1= transportService.findById(id);
        if(transportMethod1.isPresent()){
            model.addAttribute("transportMethod1",transportMethod1.get());
        }else {
            model.addAttribute("transport",new TransportMethod());
        }
        return "/method/method1";
    }
    @GetMapping("/removePayment/{id}")
    public String remove(@PathVariable(name = "id")Integer id){
        paymentService.deleteById(id);
        return "redirect:/method/showAllPayment";
    }
    @GetMapping("/removeTransport/{id}")
    public String remove1(@PathVariable(name = "id")Integer id){
        transportService.deleteById(id);
        return "redirect:/method/showAllTransport";
    }
}
