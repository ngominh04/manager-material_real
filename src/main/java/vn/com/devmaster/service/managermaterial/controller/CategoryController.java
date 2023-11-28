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
import vn.com.devmaster.service.managermaterial.domain.PaymentMethod;
import vn.com.devmaster.service.managermaterial.reponsitory.categoryRespon;
import vn.com.devmaster.service.managermaterial.service.CategoryService;
import vn.com.devmaster.service.managermaterial.service.ParamService;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    categoryRespon categoryRespon;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ParamService paramService;

    // show toàn bộ
    @GetMapping("/showAllCategory")
    public String showAllCategory(Model model){
        model.addAttribute("showAll",categoryRespon.getCategory());
        return "/category/showCategory";
    }
    // thêm mới
    @GetMapping("/category")
    public String showForm(Model model){
        model.addAttribute("category",new Category());
        return "/category/category";
    }
    // lưu lại khi thêm mới
    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate(@Validated @ModelAttribute(name = "category") Category category,
                               BindingResult result,
                               @RequestParam("icon") MultipartFile multipartFile,
                               Model model,
                               HttpSession session){
//        if(result.hasErrors()){
//            model.addAttribute("message","Có thông tin bạn chưa nhập");
//            return "/category/category";
//        }
        // thêm ảnh
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String uploadDir = "uploads/";
        category.setIcon(fileName);
        paramService.save(multipartFile,uploadDir);
        // cập nhật tên người thêm
        String createBy = (String) session.getAttribute("saveNameCustomer");
        category.setCreatedBy(createBy);
         //
        session.setAttribute("saveFile",paramService.save(multipartFile,uploadDir));

        categoryService.save(category);
        model.addAttribute("category",new Category());

        return "redirect:/category/showAllCategory";
    }
    // lưu lại khi sửa
    @PostMapping("/saveOrUpdate/{id}")
    public String saveOrUpdate(@Validated @ModelAttribute(name = "category") Category category,
                               BindingResult result,
                               Model model,
                               @RequestParam("icon") MultipartFile multipartFile,
                               @PathVariable(name = "id") Integer id,
                               HttpSession session){

        // cập nhật tên người sửa
        String updateBy = (String) session.getAttribute("saveNameCustomer");
        category.setUpdatedBy(updateBy);
        // cập nhật lên payment để sửa
        // cập nhật ảnh
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String uploadDir = "uploads/";
        category.setIcon(fileName);
        paramService.save(multipartFile,uploadDir);
        //
        Optional<Category> category1= categoryService.findById(id);
        if(category1.isPresent()){
            model.addAttribute("category1",category1.get());
            categoryService.save(category);
            return "redirect:/category/showAllCategory";
        }else {
            model.addAttribute("category",new Category());
        }
        return "redirect:/category/showAllCategory";
    }
    // click vào để sửa
    @GetMapping("/category/{id}")
    public String edit(@ModelAttribute(name = "category") Category category,
                       @PathVariable(name = "id") Integer id,Model model,HttpSession session){
        String createBy = (String) session.getAttribute("saveNameCustomer");
        category.setCreatedBy(createBy);
        Optional<Category> category1= categoryService.findById(id);
        if(category1.isPresent()){
            model.addAttribute("category1",category1.get());
        }else {
            model.addAttribute("category",new Category());
        }
        return "/category/category";
    }
    @GetMapping("/off/{id}")
    public String off(@PathVariable(name = "id")Integer id){
        Category category = categoryRespon.finAllById(id);
        category.setIsdelete(0);
        categoryService.save(category);
        return "redirect:/category/showAllCategory";
    }
    @GetMapping("/on/{id}")
    public String on(@PathVariable(name = "id")Integer id){
        Category category = categoryRespon.finAllById(id);
        category.setIsdelete(1);
        categoryService.save(category);
        return "redirect:/category/showAllCategory";
    }
}
