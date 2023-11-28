package vn.com.devmaster.service.managermaterial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.devmaster.service.managermaterial.domain.Category;
import vn.com.devmaster.service.managermaterial.reponsitory.categoryRespon;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    categoryRespon categoryRespon;

    public Category save(Category entity) {
        entity.setIsactive((byte) 1);
        LocalDateTime Date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        entity.setCreatedDate(Date.format(formatter));
        entity.setUpdatedDate(Date.format(formatter));
        return categoryRespon.save(entity);
    }

    public List<Category> saveAll(List<Category> entities) {
        return (List<Category>) categoryRespon.saveAll(entities);
    }

    public Optional<Category> findById(Integer integer) {
        return categoryRespon.findById(integer);
    }

    public boolean existsById(Integer integer) {
        return categoryRespon.existsById(integer);
    }

    public List<Category> findAll() {
        return (List<Category>) categoryRespon.findAll();
    }

    public List<Category> findAllById(List<Integer> integers) {
        return (List<Category>) categoryRespon.findAllById(integers);
    }

    public long count() {
        return categoryRespon.count();
    }

    public void deleteById(Integer integer) {
        categoryRespon.deleteById(integer);
    }

    public void delete(Category entity) {
        categoryRespon.delete(entity);
    }

    public void deleteAllById(List<Integer> integers) {
        categoryRespon.deleteAllById(integers);
    }

    public void deleteAll(List<Category> entities) {
        categoryRespon.deleteAll(entities);
    }

    public void deleteAll() {
        categoryRespon.deleteAll();
    }
}
