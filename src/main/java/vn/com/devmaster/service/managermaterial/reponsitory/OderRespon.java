package vn.com.devmaster.service.managermaterial.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.com.devmaster.service.managermaterial.domain.Order;

public interface OderRespon extends JpaRepository<Order,Integer> {
}
