package vn.com.devmaster.service.managermaterial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.com.devmaster.service.managermaterial.domain.Category;
import vn.com.devmaster.service.managermaterial.domain.Product;
import vn.com.devmaster.service.managermaterial.reponsitory.ProductRespon;
import vn.com.devmaster.service.managermaterial.service.ParamService;
import vn.com.devmaster.service.managermaterial.service.ProductService;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductRespon productRespon;
    @Autowired
    ProductService productService;
    @Autowired
    ParamService paramService;
    // show toàn bộ
    @GetMapping("/showAllProduct")
    public String showAllProduct(Model model){
        model.addAttribute("showAll",productRespon.getAllProduct());
        return "/category/showProduct";
    }
    // thêm mới
    @GetMapping("/product")
    public String showForm(Model model){

        model.addAttribute("product",new Product());
        return "/category/product";
    }
    // lưu lại khi thêm mới
    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate(@Validated @ModelAttribute(name = "product") Product product,
                               BindingResult result,
                               @RequestParam("image") MultipartFile file,
                               Model model,
                               HttpSession session){
//        if(result.hasErrors()){
//            model.addAttribute("message","Có thông tin bạn chưa nhập");
//            return "/category/category";
//        }
        // thêm ảnh
        String fileName1 = StringUtils.cleanPath(file.getOriginalFilename());
        String uploadDir = "uploads/";
        product.setImage(fileName1);
        paramService.save(file,uploadDir);
        // cập nhật tên người thêm
        String createBy = (String) session.getAttribute("saveNameCustomer");
        product.setCreatedBy(createBy);
        //
//        session.setAttribute("saveFile",paramService.save(file,uploadDir));

        productService.save(product);
        model.addAttribute("product",new Product());

        return "redirect:/product/showAllProduct";
    }
    // lưu lại khi sửa
    @PostMapping("/saveOrUpdate/{id}")
    public String saveOrUpdate(@Validated @ModelAttribute(name = "product") Product product,
                               BindingResult result,
                               Model model,
                               @RequestParam("image") MultipartFile file,
                               @PathVariable(name = "id") Integer id,
                               HttpSession session){

        // cập nhật tên người sửa
        String updateBy = (String) session.getAttribute("saveNameCustomer");
        product.setUpdatedBy(updateBy);
        // cập nhật lên payment để sửa
        // cập nhật ảnh
        String fileName1 = StringUtils.cleanPath(file.getOriginalFilename());
        String uploadDir = "uploads/";
        product.setImage(fileName1);
        paramService.save(file,uploadDir);
        //
        Optional<Product> product1= productService.findById(id);
        if(product1.isPresent()){
            model.addAttribute("product1",product1.get());
            productService.save(product);
            return "redirect:/product/showAllProduct";
        }else {
            model.addAttribute("product",new Product());
        }
        return "redirect:/product/showAllProduct";
    }
    // click vào để sửa
    @GetMapping("/product/{id}")
    public String edit(@ModelAttribute(name = "product") Product product,
                       @PathVariable(name = "id") Integer id,
                       Model model,HttpSession session){
        String updateBy = (String) session.getAttribute("saveNameCustomer");
        product.setUpdatedBy(updateBy);
        Optional<Product> product1= productService.findById(id);
        if(product1.isPresent()){
            model.addAttribute("product1",product1.get());
        }else {
            model.addAttribute("product",new Product());
        }
        return "/category/product";
    }
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable(name = "id")Integer id){
        productService.deleteById(id);
        return "redirect:/product/showAllProduct";
    }
}
