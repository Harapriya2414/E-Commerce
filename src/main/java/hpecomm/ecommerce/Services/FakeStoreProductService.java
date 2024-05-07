package hpecomm.ecommerce.Services;

import hpecomm.ecommerce.DTO.FakeStoreProductDto;
import hpecomm.ecommerce.Models.Category;
import hpecomm.ecommerce.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Product getSingleProduct(Long id) {
 //       FakeStoreProductDto fakeStoreProductDto = restTemplate
   //            .getForObject(
     //                   "https://fakestoreapi.com/products/" + id,
       //                 FakeStoreProductDto.class);

        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(
                      "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class);


       FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();

        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImageUrl(fakeStoreProductDto.getImage());

        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        return product;
    }

    @Override
    public Product createProduct(String title, String description, String image, String category, double price) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setImage(image);
        fakeStoreProductDto.setTitle(title);

        FakeStoreProductDto response = restTemplate
                .postForObject("https://fakestoreapi.com/products",
                        fakeStoreProductDto,
                        FakeStoreProductDto.class);



        //assert response != null;
        return response.toProduct();

    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] response =
                restTemplate.getForObject("https://fakestoreapi.com/products",
                        FakeStoreProductDto[].class);

        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto: response) {
            products.add(fakeStoreProductDto.toProduct());
        }

        return products;
    }
}
