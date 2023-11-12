package vn.com.devmaster.service.managermaterial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.devmaster.service.managermaterial.domain.Nguoinhan;
import vn.com.devmaster.service.managermaterial.reponsitory.NguoiNhanRespon;

import java.util.List;
import java.util.Optional;

@Service
public class NguoiNhanService {
    @Autowired
    NguoiNhanRespon nguoiNhanRespon;

    public Nguoinhan save(Nguoinhan entity) {
        return nguoiNhanRespon.save(entity);
    }

    public List<Nguoinhan> saveAll(List<Nguoinhan> entities) {
        return (List<Nguoinhan>) nguoiNhanRespon.saveAll(entities);
    }

    public Optional<Nguoinhan> findById(Integer integer) {
        return nguoiNhanRespon.findById(integer);
    }

    public boolean existsById(Integer integer) {
        return nguoiNhanRespon.existsById(integer);
    }

    public List<Nguoinhan> findAll() {
        return (List<Nguoinhan>) nguoiNhanRespon.findAll();
    }

    public List<Nguoinhan> findAllById(List<Integer> integers) {
        return (List<Nguoinhan>) nguoiNhanRespon.findAllById(integers);
    }

    public long count() {
        return nguoiNhanRespon.count();
    }

    public void deleteById(Integer integer) {
        nguoiNhanRespon.deleteById(integer);
    }

    public void delete(Nguoinhan entity) {
        nguoiNhanRespon.delete(entity);
    }

    public void deleteAllById(List<Integer> integers) {
        nguoiNhanRespon.deleteAllById(integers);
    }

    public void deleteAll(List<Nguoinhan> entities) {
        nguoiNhanRespon.deleteAll(entities);
    }

    public void deleteAll() {
        nguoiNhanRespon.deleteAll();
    }
}
