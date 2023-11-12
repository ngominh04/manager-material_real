package vn.com.devmaster.service.managermaterial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.devmaster.service.managermaterial.domain.CartItem;
import vn.com.devmaster.service.managermaterial.reponsitory.CartItemRespon;

import java.util.*;

@Service
public class CartService  {
    @Autowired
    CartItemRespon cartItemRespon;

    public CartItem save(CartItem entity) {
        return cartItemRespon.save(entity);
    }

    public List<CartItem> saveAll(List<CartItem> entities) {
        return (List<CartItem>) cartItemRespon.saveAll(entities);
    }

    public Optional<CartItem> findById(Integer integer) {
        return cartItemRespon.findById(integer);
    }

    public boolean existsById(Integer integer) {
        return cartItemRespon.existsById(integer);
    }

    public List<CartItem> findAll() {
        return (List<CartItem>) cartItemRespon.findAll();
    }

    public List<CartItem> findAllById(List<Integer> integers) {
        return (List<CartItem>) cartItemRespon.findAllById(integers);
    }

    public long count() {
        return cartItemRespon.count();
    }

    public void deleteById(Integer integer) {
        cartItemRespon.deleteById(integer);
    }

    public void delete(CartItem entity) {
        cartItemRespon.delete(entity);
    }

    public void deleteAllById(List<Integer> integers) {
        cartItemRespon.deleteAllById(integers);
    }

    public void deleteAll(List<CartItem> entities) {
        cartItemRespon.deleteAll(entities);
    }

    public void deleteAll() {
        cartItemRespon.deleteAll();
    }
}
