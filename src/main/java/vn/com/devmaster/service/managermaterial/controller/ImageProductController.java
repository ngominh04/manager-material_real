package vn.com.devmaster.service.managermaterial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.com.devmaster.service.managermaterial.domain.ProductImage;
import vn.com.devmaster.service.managermaterial.reponsitory.ImageProductRespon;
import vn.com.devmaster.service.managermaterial.service.ImageProductService;
import vn.com.devmaster.service.managermaterial.service.ParamService;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/image")
public class ImageProductController {
    @Autowired
    ImageProductRespon imageProductRespon;
    @Autowired
    ImageProductService imageProductService;
    @Autowired
    ParamService paramService;
    // show toàn bộ
    @GetMapping("/showAllImage")
    public String showAllProduct(Model model){
        model.addAttribute("showAll",imageProductRespon.getProductImage());
        return "/category/showImage";
    }
    // thêm mới
    @GetMapping("/image")
    public String showForm(Model model){

        model.addAttribute("image",new ProductImage());
        return "/category/imageProduct";
    }
    // lưu lại khi thêm mới
    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate(@Validated @ModelAttribute(name = "productImage") ProductImage productImage,
                               BindingResult result,
                               @RequestParam("url") MultipartFile file,
                               Model model,
                               HttpSession session){
        // thêm ảnh
        String fileName1 = StringUtils.cleanPath(file.getOriginalFilename());
        String uploadDir = "uploads/";
        productImage.setUrl(fileName1);
        paramService.save(file,uploadDir);
        //
//        session.setAttribute("saveFile",paramService.save(file,uploadDir));

        imageProductService.save(productImage);
        model.addAttribute("image",new ProductImage());

        return "redirect:/image/showAllImage";
    }
    // lưu lại khi sửa
    @PostMapping("/saveOrUpdate/{id}")
    public String saveOrUpdate(@Validated @ModelAttribute(name = "productImage") ProductImage productImage,
                               BindingResult result,
                               Model model,
                               @RequestParam("url") MultipartFile file,
                               @PathVariable(name = "id") Integer id,
                               HttpSession session){

        // cập nhật lên để sửa
        // cập nhật ảnh
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uploadDir = "uploads/";
        productImage.setUrl(fileName);
        paramService.save(file,uploadDir);
        //
        Optional<ProductImage> image1= imageProductService.findById(id);
        if(image1.isPresent()){
            model.addAttribute("image1",image1.get());
            imageProductService.save(productImage);
            return "redirect:/image/showAllImage";
        }else {
            model.addAttribute("image",new ProductImage());
        }
        return "redirect:/image/showAllImage";
    }
    // click vào để sửa
    @GetMapping("/image/{id}")
    public String edit(@ModelAttribute(name = "image") ProductImage image,
                       @PathVariable(name = "id") Integer id,
                       Model model,HttpSession session){

        Optional<ProductImage> image1= imageProductService.findById(id);
        if(image1.isPresent()){
            model.addAttribute("image1",image1.get());
        }else {
            model.addAttribute("image",new ProductImage());
        }
        return "/category/imageProduct";
    }
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable(name = "id")Integer id){
        imageProductService.deleteById(id);
        return "redirect:/image/showAllImage";
    }
}
