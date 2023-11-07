package vn.com.devmaster.service.managermaterial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import vn.com.devmaster.service.managermaterial.domain.OrdersTransport;
import vn.com.devmaster.service.managermaterial.domain.TransportMethod;
import vn.com.devmaster.service.managermaterial.reponsitory.OrderTransportRespon;
import vn.com.devmaster.service.managermaterial.reponsitory.TransportRespon;

import java.util.List;
import java.util.Optional;

@Service
public class OderTransportService {
    @Autowired
    OrderTransportRespon orderTransportRespon;
    @Autowired
    TransportRespon transportRespon;

    public OrdersTransport save(OrdersTransport entity, @Param("idtransport") Integer idtransport) {
//        TransportMethod transportMethod = transportRespon.findAllById(idtransport);
        if(idtransport == 1){
            entity.setTotal(10000);
        }
        if(idtransport == 3){
            entity.setTotal(150000);
        }
        if(idtransport == 5){
            entity.setTotal(250000);
        }else {
            entity.setTotal(10000);
        }
        entity.setNotes(1);
        return orderTransportRespon.save(entity);
    }

    public List<OrdersTransport> saveAll(List<OrdersTransport> entities) {
        return (List<OrdersTransport>) orderTransportRespon.saveAll(entities);
    }

    public Optional<OrdersTransport> findById(Integer integer) {
        return orderTransportRespon.findById(integer);
    }

    public boolean existsById(Integer integer) {
        return orderTransportRespon.existsById(integer);
    }

    public List<OrdersTransport> findAll() {
        return (List<OrdersTransport>) orderTransportRespon.findAll();
    }

    public List<OrdersTransport> findAllById(List<Integer> integers) {
        return (List<OrdersTransport>) orderTransportRespon.findAllById(integers);
    }

    public long count() {
        return orderTransportRespon.count();
    }

    public void deleteById(Integer integer) {
        orderTransportRespon.deleteById(integer);
    }

    public void delete(OrdersTransport entity) {
        orderTransportRespon.delete(entity);
    }

    public void deleteAllById(List< Integer> integers) {
        orderTransportRespon.deleteAllById(integers);
    }

    public void deleteAll(List<OrdersTransport> entities) {
        orderTransportRespon.deleteAll(entities);
    }

    public void deleteAll() {
        orderTransportRespon.deleteAll();
    }
}
