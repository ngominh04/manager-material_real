package vn.com.devmaster.service.managermaterial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.devmaster.service.managermaterial.domain.*;
import vn.com.devmaster.service.managermaterial.reponsitory.OrderResponsitory;

import java.util.List;
import java.util.Optional;

@Service
public class OderService {
    @Autowired
    OrderResponsitory orderResponsitory;

    public Order save(Order entity) {
        return orderResponsitory.save(entity);
    }

    public List<Order> saveAll(List<Order> entities) {
        return (List<Order>) orderResponsitory.saveAll(entities);
    }

    public Optional<Order> findById(Integer integer) {
        return orderResponsitory.findById(integer);
    }

    public boolean existsById(Integer integer) {
        return orderResponsitory.existsById(integer);
    }

    public Iterable<Order> findAll() {
        return orderResponsitory.findAll();
    }

    public List<Order> findAllById(List<Integer> integers) {
        return (List<Order>) orderResponsitory.findAllById(integers);
    }

    public long count() {
        return orderResponsitory.count();
    }

    public void deleteById(Integer integer) {
        orderResponsitory.deleteById(integer);
    }

    public void delete(Order entity) {
        orderResponsitory.delete(entity);
    }

    public void deleteAllById(List<Integer> integers) {
        orderResponsitory.deleteAllById(integers);
    }

    public void deleteAll(List<Order> entities) {
        orderResponsitory.deleteAll(entities);
    }

    public void deleteAll() {
        orderResponsitory.deleteAll();
    }
}
