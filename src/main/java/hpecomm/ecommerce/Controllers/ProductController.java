package hpecomm.ecommerce.Controllers;

import hpecomm.ecommerce.DTO.CreateProductRequestDto;
import hpecomm.ecommerce.DTO.ErrorDto;
import hpecomm.ecommerce.DTO.FakeStoreProductDto;
import hpecomm.ecommerce.Models.Product;
import hpecomm.ecommerce.Services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(@Qualifier("selfProductService") ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDto productRequestDto) {
        return productService.createProduct(
                productRequestDto.getTitle(),
                productRequestDto.getDescription(),
                productRequestDto.getImage(),
                productRequestDto.getCategory(),
                productRequestDto.getPrice()
        );
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> responseData = productService.getAllProducts();

        ResponseEntity<List<Product>> responseEntity =
                new ResponseEntity<>(responseData, HttpStatusCode.valueOf(202));

        return responseEntity;
    }


    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
         Product product= productService.getSingleProduct(id);
        return product;
    }


    /*
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDto> handleNullPointerException() {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage("Something went wrong. Please try again");
        return new ResponseEntity<>(errorDto,
                        HttpStatusCode.valueOf(404));
    }

 */
}
