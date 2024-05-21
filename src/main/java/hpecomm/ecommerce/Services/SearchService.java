package hpecomm.ecommerce.Services;

import hpecomm.ecommerce.Models.Product;
import hpecomm.ecommerce.Repository.ProductRepository;
//import org.springframework.data.domain.Page;
import org.springframework.data.domain.*;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
    private ProductRepository productRepository;

    public SearchService(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    public Page<Product> search(String query, int pageNumber, int pageSize) {
        Sort sort = Sort.by("title").descending()
                .and(Sort.by("Price")).ascending();

//        List<String> sortValues = new ArrayList<>();
//        for(String sortValue: sortValues) {
//            sort = Sort.by(sortValue).ascending();
//        }


        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return productRepository.findByTitleContaining(query, pageable);
    }
}
