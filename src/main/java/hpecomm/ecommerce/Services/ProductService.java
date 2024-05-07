package hpecomm.ecommerce.Services;

import hpecomm.ecommerce.Models.Product;

import java.util.List;

public interface ProductService {
    public Product getSingleProduct(Long id);
    public Product createProduct(String title, String description,
                                 String image, String category, double price);
    public List<Product> getAllProducts();
}
