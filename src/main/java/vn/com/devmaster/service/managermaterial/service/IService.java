package vn.com.devmaster.service.managermaterial.service;

import vn.com.devmaster.service.managermaterial.domain.CartItem;

import java.util.Collection;

public interface IService {


    void add(CartItem item);

    void remove(Integer id);

    CartItem update(Integer id, int qty);

    void clear();

    Collection<CartItem> getAllItem();

    int getCount();

    double getAmount();
}
