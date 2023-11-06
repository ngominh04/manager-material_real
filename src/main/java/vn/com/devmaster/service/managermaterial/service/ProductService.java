package vn.com.devmaster.service.managermaterial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import vn.com.devmaster.service.managermaterial.domain.Product;
import vn.com.devmaster.service.managermaterial.reponsitory.ProductRespon;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRespon productRespon;


    public Product save(Product entity) {
        entity.setIsactive((byte) 1);
        LocalDateTime Date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        entity.setCreatedDate(Date.format(formatter));
        entity.setUpdatedDate(Date.format(formatter));
        return productRespon.save(entity);
    }

    public List<Product> saveAll(List<Product> entities) {
        return (List<Product>) productRespon.saveAll(entities);
    }

    public Optional<Product> findById(Integer integer) {
        return productRespon.findById(integer);
    }

    public boolean existsById(Integer integer) {
        return productRespon.existsById(integer);
    }

    public List<Product> findAll() {
        return (List<Product>) productRespon.findAll();
    }

    public List<Product> findAllById(List<Integer> integers) {
        return (List<Product>) productRespon.findAllById(integers);
    }

    public long count() {
        return productRespon.count();
    }

    public void deleteById(Integer integer) {
        productRespon.deleteById(integer);
    }

    public void delete(Product entity) {
        productRespon.delete(entity);
    }

    public void deleteAllById(List<Integer> integers) {
        productRespon.deleteAllById(integers);
    }

    public void deleteAll(List<Product> entities) {
        productRespon.deleteAll(entities);
    }

    public void deleteAll() {
        productRespon.deleteAll();
    }
}
