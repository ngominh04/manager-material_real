package vn.com.devmaster.service.managermaterial.service;


import org.springframework.beans.factory.annotation.Autowired;
import vn.com.devmaster.service.managermaterial.domain.CartItem;
import vn.com.devmaster.service.managermaterial.reponsitory.ProductRespon;


import javax.transaction.Transactional;
import java.util.*;

@Transactional
@org.springframework.stereotype.Service
public class Service implements IService{
    @Autowired
    private ProductRespon productRespon;

    Map<Integer, CartItem> maps= new HashMap<>();
    @Override
    public void add(CartItem item){
        CartItem cartItem = maps.get(item.getId());
        if(cartItem == null){
            maps.put(item.getId(),item);
        }else {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        }
    }
    @Override
    public void remove(Integer id){
        maps.remove(id);
    }
    @Override
    public CartItem update(Integer id, int qty){
        CartItem  cartItem= maps.get(id);
        cartItem.setQuantity(qty);
        return cartItem;
    }
    @Override
    public void clear(){
        maps.clear();
    }
    @Override
    public Collection<CartItem> getAllItem(){
        return maps.values();
    }
    @Override
    public int getCount(){
        return maps.values().size();
    }
    @Override
    public double getAmount(){
        return maps.values().stream().mapToDouble(item -> item.getQuantity() * item.getPrice()).sum();
    }
}
