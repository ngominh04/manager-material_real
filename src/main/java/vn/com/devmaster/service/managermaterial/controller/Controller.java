package vn.com.devmaster.service.managermaterial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.com.devmaster.service.managermaterial.domain.Category;
import vn.com.devmaster.service.managermaterial.domain.Customer;
import vn.com.devmaster.service.managermaterial.domain.Product;
import vn.com.devmaster.service.managermaterial.projecttion.IProduct;
import vn.com.devmaster.service.managermaterial.reponsitory.ProductRespon;
import vn.com.devmaster.service.managermaterial.reponsitory.Responsitory;
import vn.com.devmaster.service.managermaterial.service.Service;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    Responsitory responsitory;
    @Autowired
    ProductRespon productRespon;
    @Autowired
    Service service;

    @GetMapping("/category")
    List<Category> getCategory() {
        return responsitory.getCategory();
    }

    @GetMapping("/customer")
    List<Customer> getCustomer() {
        return responsitory.getCustomer();}

    @GetMapping("/products")
    List<IProduct> getProduct(){
        return  responsitory.getProduct();
    }

//    @GetMapping("/finById/{id}")
//    Optional<Product> getById(@PathVariable Integer id){return service.finById(id);}

//    @GetMapping("/getById")
//    public ResponseEntity<List<Product>> getProductById(@RequestParam Integer id){
//        return new ResponseEntity<>(productRespon.findByName(id), HttpStatus.OK);
//    }
}
