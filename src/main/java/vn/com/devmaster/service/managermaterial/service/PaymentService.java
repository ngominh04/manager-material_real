package vn.com.devmaster.service.managermaterial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.devmaster.service.managermaterial.domain.PaymentMethod;
import vn.com.devmaster.service.managermaterial.reponsitory.PaymentRespon;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    PaymentRespon paymentRespon;

    public PaymentMethod save(PaymentMethod entity) {
        entity.setIsactive((byte) 1);
        LocalDateTime Date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        entity.setCreatedDate(Date.format(formatter));
        entity.setUpdatedDate(Date.format(formatter));
        return paymentRespon.save(entity);
    }

    public List<PaymentMethod> saveAll(List<PaymentMethod> entities) {
        return (List<PaymentMethod>) paymentRespon.saveAll(entities);
    }

    public Optional<PaymentMethod> findById(Integer integer) {
        return paymentRespon.findById(integer);
    }

    public boolean existsById(Integer integer) {
        return paymentRespon.existsById(integer);
    }

    public List<PaymentMethod> findAll() {
        return (List<PaymentMethod>) paymentRespon.findAll();
    }

    public List<PaymentMethod> findAllById(List<Integer> integers) {
        return (List<PaymentMethod>) paymentRespon.findAllById(integers);
    }

    public long count() {
        return paymentRespon.count();
    }

    public void deleteById(Integer integer) {
        paymentRespon.deleteById(integer);
    }

    public void delete(PaymentMethod entity) {
        paymentRespon.delete(entity);
    }

    public void deleteAllById(List<Integer> integers) {
        paymentRespon.deleteAllById(integers);
    }

    public void deleteAll(List<PaymentMethod> entities) {
        paymentRespon.deleteAll(entities);
    }

    public void deleteAll() {
        paymentRespon.deleteAll();
    }
}
