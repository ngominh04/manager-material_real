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
    // show chi tiết product
    @GetMapping("/showChiTietProduct/{idPro}")
    public String showChiTietProduct(Model model,@PathVariable(name = "idPro") Integer idPro){
        Product product=productRespon.findAllById(idPro);
        model.addAttribute("show",product);
        return "/category/showChiTietProduct";
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
        product.setIsDelete(1);
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
    //  trang showAll
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable(name = "id")Integer id){
        Product product = productRespon.findAllById(id);
        product.setIsDelete(0);
        productRespon.save(product);
        return "redirect:/product/showAllProduct";
    }

    @GetMapping("/on/{id}")
    public String on(@PathVariable(name = "id")Integer id){
        Product product = productRespon.findAllById(id);
        product.setIsDelete(1);
        productRespon.save(product);
        return "redirect:/product/showAllProduct";
    }
    // trang showChiTIet
    @GetMapping("/remove1/{id}")
    public String remove1(@PathVariable(name = "id")Integer id){
        Product product = productRespon.findAllById(id);
        product.setIsDelete(0);
        productRespon.save(product);
        return "redirect:/product/showChiTietProduct/{id}";
    }

    @GetMapping("/on1/{id}")
    public String on1(@PathVariable(name = "id")Integer id){
        Product product = productRespon.findAllById(id);
        product.setIsDelete(1);
        productRespon.save(product);
        return "redirect:/product/showChiTietProduct/{id}";
    }
    @GetMapping("/back")
    public String back(Model model){
        return "redirect:/product/showAllProduct";
    }
}
