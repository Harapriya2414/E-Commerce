package hpecomm.ecommerce.Controllers;

import hpecomm.ecommerce.DTO.CreateProductRequestDto;
import hpecomm.ecommerce.DTO.ErrorDto;
import hpecomm.ecommerce.DTO.FakeStoreProductDto;
import hpecomm.ecommerce.Models.Product;
import hpecomm.ecommerce.Services.ProductService;
import hpecomm.ecommerce.advices.NotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws NotFoundException {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProduct() throws NotFoundException {
        return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
    }

    @PostMapping()
    public Product createProduct(@RequestBody CreateProductRequestDto productRequestDto){
        return productService.createProduct(
                productRequestDto.getTitle(),
                productRequestDto.getDescription(),
                productRequestDto.getImage(),
                productRequestDto.getCategory(),
                productRequestDto.getPrice()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id) throws NotFoundException {

        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.NO_CONTENT);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody CreateProductRequestDto productRequestDto) throws NotFoundException {
        return new ResponseEntity<>(productService.updateProduct(id,
                productRequestDto.getTitle(),
                productRequestDto.getDescription(),
                productRequestDto.getImage(),
                productRequestDto.getCategory(),
                productRequestDto.getPrice()),HttpStatus.OK);
    }

    @GetMapping("/categories/{title}")
    public ResponseEntity<List<Product>> getAllProductByCategory(@PathVariable("title") String title) throws NotFoundException {
        return new ResponseEntity<>(productService.getAllProductByCategory(title),
                HttpStatus.OK);
    }

}
