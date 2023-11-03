package vn.com.devmaster.service.managermaterial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.devmaster.service.managermaterial.domain.OrdersPayment;
import vn.com.devmaster.service.managermaterial.reponsitory.OrderPaymentRespon;

import java.util.List;
import java.util.Optional;

@Service
public class OrderPaymentService {
    @Autowired
    OrderPaymentRespon orderPaymentRespon;

    public OrdersPayment save(OrdersPayment entity) {
        return orderPaymentRespon.save(entity);
    }

    public List<OrdersPayment> saveAll(List<OrdersPayment> entities) {
        return (List<OrdersPayment>) orderPaymentRespon.saveAll(entities);
    }

    public Optional<OrdersPayment> findById(Integer integer) {
        return orderPaymentRespon.findById(integer);
    }

    public boolean existsById(Integer integer) {
        return orderPaymentRespon.existsById(integer);
    }

    public List<OrdersPayment> findAll() {
        return (List<OrdersPayment>) orderPaymentRespon.findAll();
    }

    public List<OrdersPayment> findAllById(List<Integer> integers) {
        return (List<OrdersPayment>) orderPaymentRespon.findAllById(integers);
    }

    public long count() {
        return orderPaymentRespon.count();
    }

    public void deleteById(Integer integer) {
        orderPaymentRespon.deleteById(integer);
    }

    public void delete(OrdersPayment entity) {
        orderPaymentRespon.delete(entity);
    }

    public void deleteAllById(Iterable<? extends Integer> integers) {
        orderPaymentRespon.deleteAllById(integers);
    }

    public void deleteAll(List<OrdersPayment> entities) {
        orderPaymentRespon.deleteAll(entities);
    }

    public void deleteAll() {
        orderPaymentRespon.deleteAll();
    }
}
