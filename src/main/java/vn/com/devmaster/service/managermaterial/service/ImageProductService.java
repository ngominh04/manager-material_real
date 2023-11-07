package vn.com.devmaster.service.managermaterial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.devmaster.service.managermaterial.domain.ProductImage;
import vn.com.devmaster.service.managermaterial.reponsitory.ImageProductRespon;

import java.util.List;
import java.util.Optional;

@Service
public class ImageProductService {
    @Autowired
    ImageProductRespon imageProductRespon;

    public ProductImage save(ProductImage entity) {
        return imageProductRespon.save(entity);
    }

    public List<ProductImage> saveAll(List<ProductImage> entities) {
        return (List<ProductImage>) imageProductRespon.saveAll(entities);
    }

    public Optional<ProductImage> findById(Integer integer) {
        return imageProductRespon.findById(integer);
    }

    public boolean existsById(Integer integer) {
        return imageProductRespon.existsById(integer);
    }

    public List<ProductImage> findAll() {
        return (List<ProductImage>) imageProductRespon.findAll();
    }

    public List<ProductImage> findAllById(List<Integer> integers) {
        return (List<ProductImage>) imageProductRespon.findAllById(integers);
    }

    public long count() {
        return imageProductRespon.count();
    }

    public void deleteById(Integer integer) {
        imageProductRespon.deleteById(integer);
    }

    public void delete(ProductImage entity) {
        imageProductRespon.delete(entity);
    }

    public void deleteAllById(List< Integer> integers) {
        imageProductRespon.deleteAllById(integers);
    }

    public void deleteAll(List<ProductImage> entities) {
        imageProductRespon.deleteAll(entities);
    }

    public void deleteAll() {
        imageProductRespon.deleteAll();
    }
}
