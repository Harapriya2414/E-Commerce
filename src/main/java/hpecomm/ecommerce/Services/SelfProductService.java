package hpecomm.ecommerce.Services;

import hpecomm.ecommerce.Models.Category;
import hpecomm.ecommerce.Models.Product;
import hpecomm.ecommerce.Repository.CategoryRepository;
import hpecomm.ecommerce.Repository.ProductRepository;
import hpecomm.ecommerce.advices.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductById(Long id) throws NotFoundException {
        Optional<Product> response = productRepository.findById(id);
        if(response.isPresent()){
            return response.get();
        }
        throw new NotFoundException("Product with id:"+id+" doesn't exist");
    }

    @Override
    public List<Product> getAllProduct() throws NotFoundException {
        List<Product> response = productRepository.findAll();
        if(!response.isEmpty()){
            return response;
        }
        throw new NotFoundException("There is no products");
    }

    @Override
    public Product createProduct(String title, String description,
                                 String image, String category, double price) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setImageUrl(image);

        Category categoryFromDatabase = categoryRepository.findByTitle(category);
        if (categoryFromDatabase == null) {
            categoryFromDatabase = new Category();
            categoryFromDatabase.setTitle(category);
        }
        product.setCategory(categoryFromDatabase);
        product.setPrice(price);

        return productRepository.save(product);
    }

    @Override
    public Product deleteProduct(Long id) throws NotFoundException {
        Optional<Product> productFromDb = productRepository.findById(id);
        if(productFromDb.isPresent()) {
            productRepository.deleteById(id);
            return productFromDb.get();
        }
        throw new NotFoundException("Product with id:"+id+" doesn't exist");
    }

    @Override
    public Product updateProduct(Long id, String title, String description,
                                 String image, String category, double price) throws NotFoundException {
        Optional<Product> response = productRepository.findById(id);
        if(response.isPresent()) {
            Product currentProduct = response.get();
            if (title != null) {
                currentProduct.setTitle(title);
            }
            if (description != null) {
                currentProduct.setDescription(description);
            }
            if (image != null) {
                currentProduct.setImageUrl(image);
            }
            if (category == null) {
                currentProduct.setCategory(currentProduct.getCategory());
            }
            if (price != 0) {
                currentProduct.setPrice(price);
            }
            else {
                Category categoryFromDb = categoryRepository.findByTitle(category);
                if (categoryFromDb == null) {
                    categoryFromDb = new Category();
                    categoryFromDb.setTitle(category);
                }
                currentProduct.setCategory(categoryFromDb);
            }
            return productRepository.save(currentProduct);
        }

        throw new NotFoundException("Product with id:"+id+" doesn't exist");
    }

    @Override
    public List<Product> getAllProductByCategory(String title) throws NotFoundException {
        List<Product> response = productRepository.findByCategoryTitle(title);
        if(!response.isEmpty()){
            return response;
        }
        throw new NotFoundException("There is no product in "+title+" category");
    }
}
