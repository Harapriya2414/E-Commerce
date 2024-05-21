package hpecomm.ecommerce.Repository;

import hpecomm.ecommerce.Models.Product;
import hpecomm.ecommerce.Repository.Projection.ProductWithTitleAndId;
//import org.apache.el.stream.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findById(Long id);

    Product save(Product product);

    List<Product> findAll();

    void deleteById(Long id);

    List<Product> findByCategoryTitle(String title);

    Page<Product> findByTitleContaining(String title, Pageable pageable);



}

