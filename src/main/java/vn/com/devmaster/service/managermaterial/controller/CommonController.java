package vn.com.devmaster.service.managermaterial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.com.devmaster.service.managermaterial.domain.Product;
import vn.com.devmaster.service.managermaterial.projecttion.IProduct;
import vn.com.devmaster.service.managermaterial.reponsitory.CustomerRespon;
import vn.com.devmaster.service.managermaterial.reponsitory.Responsitory;

import java.util.List;

@Controller
public class CommonController {
    @Autowired
    Responsitory responsitory;
    @Autowired
    CustomerRespon customerRespon;
    @GetMapping("")
    public String showIndex(Model model){
        List<IProduct> product= responsitory.getProductTC();
        model.addAttribute("trangChu",product);
        return "layout/index";
    }

    @GetMapping("/index1")
    public String showIndex1(Model model, @RequestParam(name = "username")String username){
        model.addAttribute("trangChu",responsitory.getProductTC());
        model.addAttribute("nameCustom",customerRespon.getCustomer1(username));
        return "layout/index1";
    }

}
