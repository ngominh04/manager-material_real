package vn.com.devmaster.service.managermaterial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.com.devmaster.service.managermaterial.domain.Customer;
import vn.com.devmaster.service.managermaterial.domain.Nguoinhan;
import vn.com.devmaster.service.managermaterial.domain.PaymentMethod;
import vn.com.devmaster.service.managermaterial.reponsitory.CustomerRespon;
import vn.com.devmaster.service.managermaterial.reponsitory.NguoiNhanRespon;
import vn.com.devmaster.service.managermaterial.service.NguoiNhanService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/deciver")
public class DeciverController {
    @Autowired
    NguoiNhanRespon nguoiNhanRespon;
    @Autowired
    NguoiNhanService nguoiNhanService;
    @Autowired
    CustomerRespon customerRespon;

    @GetMapping("/showAllDeciver/{username}")
    public String showAllPayment(Model model, @PathVariable(name = "username")String username){

        Customer customer= customerRespon.getCustomer1(username);
        List<Nguoinhan> nguoinhan = nguoiNhanRespon.getNguoinhan(customer.getId());
        model.addAttribute("listNguoiNhan",nguoinhan);
        model.addAttribute("showAll",nguoinhan);
        return "/deciver/showDeciver";
    }
    @GetMapping("/deciver")
    public String showForm(Model model){
        model.addAttribute("deciver",new Nguoinhan());
        return "/deciver/deciver";
    }
    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate(@Validated @ModelAttribute(name = "deciver") Nguoinhan nguoinhan,
                               BindingResult result,
                               Model model, HttpSession session){
        Customer customer= (Customer) session.getAttribute("saveCus");
        nguoinhan.setIdCustomer(customer.getId());
        nguoiNhanService.save(nguoinhan);
        model.addAttribute("deciver",new Nguoinhan());

        return "redirect:/view/products";
    }
    @PostMapping("/saveOrUpdate/{username}/{id}")
    public String saveOrUpdate(@Validated @ModelAttribute(name = "deciver") Nguoinhan nguoinhan,
                               BindingResult result,
                               Model model,
                               @PathVariable(name = "id") Integer id,
                               @PathVariable(name = "username") String username){
        Customer customer= customerRespon.getCustomer1(username);
        // cập nhật lên để sửa
        Optional<Nguoinhan> nguoinhan1= nguoiNhanService.findById(id);
        if(nguoinhan1.isPresent()){
            model.addAttribute("nguoinhan1",nguoinhan1.get());
            nguoinhan.setIdCustomer(customer.getId());
            nguoiNhanService.save(nguoinhan);
            return "redirect:/deciver/showAllDeciver/{username}";
        }else {
            model.addAttribute("deciver",new Nguoinhan());
        }
        return "redirect:/deciver/showAllDeciver/{username}";
    }
    @GetMapping("/deciver/{id}")
    public String edit(@PathVariable(name = "id") Integer id,Model model){
        Optional<Nguoinhan> nguoinhan1= nguoiNhanService.findById(id);
        if(nguoinhan1.isPresent()){
            model.addAttribute("deciver1",nguoinhan1.get());
        }else {
            model.addAttribute("deciver",new Nguoinhan());
        }
        return "/deciver/deciver";
    }
    @GetMapping("/remove/{username}/{id}")
    public String remove(@PathVariable(name = "id")Integer id,@PathVariable(name = "username")String username){
        customerRespon.getCustomer1(username);
        nguoiNhanService.deleteById(id);
        return "redirect:/deciver/showAllDeciver/{username}";
    }
}
