package vn.com.devmaster.service.managermaterial.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.devmaster.service.managermaterial.domain.Customer;
import vn.com.devmaster.service.managermaterial.reponsitory.CustomerDao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerDao customerDao;

    public Customer save(Customer entity) {
        LocalDateTime Date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        entity.setCreatedDate(Date.format(formatter));
        entity.setIsactive((byte) 1);
        entity.setPhanquyen((byte) 0);
        return customerDao.save(entity);
    }

    public List<Customer> saveAll(List<Customer> entities) {
        return (List<Customer>) customerDao.saveAll(entities);
    }

    public Optional<Customer> findById(Integer integer) {
        return customerDao.findById(integer);
    }

    public boolean existsById(Integer integer) {
        return customerDao.existsById(integer);
    }

    public List<Customer> findAll() {
        return (List<Customer>) customerDao.findAll();
    }

    public List<Customer> findAllById(List<Integer> integers) {
        return (List<Customer>)customerDao.findAllById(integers);
    }

    public long count() {
        return customerDao.count();
    }

    public void deleteById(Integer integer) {
        customerDao.deleteById(integer);
    }

    public void delete(Customer entity) {
        customerDao.delete(entity);
    }

    public void deleteAllById(List< Integer> integers) {
        customerDao.deleteAllById(integers);
    }

    public void deleteAll(List<Customer> entities) {
        customerDao.deleteAll(entities);
    }

    public void deleteAll() {
        customerDao.deleteAll();
    }

}
