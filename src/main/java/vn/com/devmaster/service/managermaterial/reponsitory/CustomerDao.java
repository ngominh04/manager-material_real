package vn.com.devmaster.service.managermaterial.reponsitory;

import org.springframework.data.repository.CrudRepository;
import vn.com.devmaster.service.managermaterial.domain.Customer;

public interface CustomerDao extends CrudRepository<Customer,Integer> {

}
