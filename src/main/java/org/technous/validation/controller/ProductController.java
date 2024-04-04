package org.technous.validation.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.technous.validation.model.Product;
import org.technous.validation.request.ProductRequest;
import org.technous.validation.service.ProductService;
import org.technous.validation.util.ApiResponse;

import java.util.List;
@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/admin/products")
    public ResponseEntity<Product> addProduct(@RequestBody ProductRequest product){
        Product mProduct = productService.addProduct(product);
        return new ResponseEntity<>(mProduct, HttpStatus.OK);
    }
    @GetMapping("/products/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts(){
      List<Product> products =  productService.getAllProduct();
      return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
    }

    @PutMapping("/updateProduct/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") Long productId,@RequestBody Product product){
        Product products = productService.updateProduct(productId,product);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/products/id/{productId}")
    public ResponseEntity<Product> findById(@PathVariable("productId")Long productId){
        Product product=productService.findByProductId(productId);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @DeleteMapping("/admin/products/{productId}/delete")
    public ResponseEntity<ApiResponse> delete(@PathVariable("productId")Long productId){
        productService.deleteProduct(productId);
        ApiResponse res = new ApiResponse();
        res.setMessage("product deleted");
        res.setStatus(true);
       return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/filterProducts")
    public ResponseEntity<List<Product>> findProductByCategoryHandler(
            @RequestParam(required = false) List<String> colors,
            @RequestParam(required = false) List<String> sizes,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) Integer minDiscount,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String stock
    ) {
        List<Product> products = productService.getAllProductt(colors, sizes, minPrice, maxPrice, minDiscount, sort, stock);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}


