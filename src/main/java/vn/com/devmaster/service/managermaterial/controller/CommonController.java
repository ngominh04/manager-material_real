package vn.com.devmaster.service.managermaterial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.com.devmaster.service.managermaterial.reponsitory.CustomerRespon;
import vn.com.devmaster.service.managermaterial.reponsitory.Responsitory;

@Controller
public class CommonController {
    @Autowired
    Responsitory responsitory;
    @Autowired
    CustomerRespon customerRespon;
    @GetMapping("")
    public String showIndex(Model model){
        model.addAttribute("trangChu",responsitory.getProductTC());
        return "layout/index";
    }

    @GetMapping("/index1")
    public String showIndex1(Model model, @RequestParam(name = "username")String username){
        model.addAttribute("trangChu",responsitory.getProductTC());
        model.addAttribute("nameCustom",customerRespon.getCustomer1(username));
        return "layout/index1";
    }

}
