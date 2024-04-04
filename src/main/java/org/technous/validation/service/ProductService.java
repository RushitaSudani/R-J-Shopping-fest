package org.technous.validation.service;

import org.springframework.data.domain.Page;
import org.technous.validation.model.Product;
import org.technous.validation.request.ProductRequest;

import java.util.List;

public interface ProductService {
    public Product addProduct(ProductRequest product);
    public String deleteProduct(Long productId);
    public Product updateProduct(Long productId,Product req);
    public Product findByProductId(Long productId);
    public List<Product> getAllProduct();
    public List<Product> getAllProductt
            (
             List<String> color,
             List<String> size ,
             Integer minPrice,
             Integer MaxPrice,
             Integer minDiscount,
             String sort,
             String stock);

}
