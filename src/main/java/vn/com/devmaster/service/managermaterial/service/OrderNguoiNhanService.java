package vn.com.devmaster.service.managermaterial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.devmaster.service.managermaterial.domain.OrdersNguoinhan;
import vn.com.devmaster.service.managermaterial.reponsitory.OrderNguoiNhanRespon;

import java.util.List;
import java.util.Optional;

@Service
public class OrderNguoiNhanService {
    @Autowired
    OrderNguoiNhanRespon nguoiNhanRespon;

    public OrdersNguoinhan save(OrdersNguoinhan entity) {
        return nguoiNhanRespon.save(entity);
    }

    public List<OrdersNguoinhan> saveAll(List<OrdersNguoinhan> entities) {
        return (List<OrdersNguoinhan>) nguoiNhanRespon.saveAll(entities);
    }

    public Optional<OrdersNguoinhan> findById(Integer integer) {
        return nguoiNhanRespon.findById(integer);
    }

    public boolean existsById(Integer integer) {
        return nguoiNhanRespon.existsById(integer);
    }

    public List<OrdersNguoinhan> findAll() {
        return (List<OrdersNguoinhan>) nguoiNhanRespon.findAll();
    }

    public List<OrdersNguoinhan> findAllById(List<Integer> integers) {
        return (List<OrdersNguoinhan>) nguoiNhanRespon.findAllById(integers);
    }

    public long count() {
        return nguoiNhanRespon.count();
    }

    public void deleteById(Integer integer) {
        nguoiNhanRespon.deleteById(integer);
    }

    public void delete(OrdersNguoinhan entity) {
        nguoiNhanRespon.delete(entity);
    }

    public void deleteAllById(List<Integer> integers) {
        nguoiNhanRespon.deleteAllById(integers);
    }

    public void deleteAll(List<OrdersNguoinhan> entities) {
        nguoiNhanRespon.deleteAll(entities);
    }

    public void deleteAll() {
        nguoiNhanRespon.deleteAll();
    }
}
