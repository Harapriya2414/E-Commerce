package hpecomm.ecommerce.Repository;

import hpecomm.ecommerce.Models.Category;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    Category findByTitle(String title);

    Category save(Category category);

    Optional<Category> findById(Long id);
}
