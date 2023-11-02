package vn.com.devmaster.service.managermaterial.service;

import org.springframework.stereotype.Service;
import vn.com.devmaster.service.managermaterial.domain.CartItem;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Transactional
@Service
public class CartService implements IService {
    Map<Integer, CartItem> maps= new HashMap<>();

    @Override
    public void add(CartItem item) {

    }

    @Override
    public void remove(Integer id) {

    }

    @Override
    public CartItem update(Integer id, int qty) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public Collection<CartItem> getAllItem(){
        return maps.values();
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public double getAmount() {
        return 0;
    }
}
