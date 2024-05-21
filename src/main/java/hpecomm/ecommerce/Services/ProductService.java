package hpecomm.ecommerce.Services;

import hpecomm.ecommerce.Models.Product;
import hpecomm.ecommerce.advices.NotFoundException;

import java.util.List;

public interface ProductService {

    Product getProductById(Long id) throws NotFoundException;

    List<Product> getAllProduct() throws NotFoundException;

    Product createProduct(String title, String description,
                          String image, String category, double price);
    Product deleteProduct(Long id) throws NotFoundException;


    Product updateProduct(Long id, String title, String description,
                          String image, String category, double price) throws NotFoundException;

    List<Product> getAllProductByCategory(String title) throws NotFoundException;
}
