package vn.com.devmaster.service.managermaterial.reponsitory;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.managermaterial.domain.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface categoryRespon extends CrudRepository<Category, Integer> {
    @Query(value = "select * from category where ID = ?",nativeQuery = true)
    Category finAllById(Integer id);
    @Query(value = "select * from category",nativeQuery = true)
    List<Category> getCategory();
}
