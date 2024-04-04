package org.technous.validation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.technous.validation.model.Product;
import org.technous.validation.repository.CategoryRepositoty;
import org.technous.validation.repository.ProductRepository;
import org.technous.validation.request.ProductRequest;
import org.technous.validation.service.ProductService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@CrossOrigin("192.168.29.236")
public class ProductServiceIMPL implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepositoty categoryRepositoty;

    public Product addProduct(ProductRequest product) {
        Product product1 = new Product();
        product1.setTitle(product.getTitle());
        product1.setBrand(product.getBrand());
        product1.setColor(product.getColor());
        product1.setDescription(product.getDescription());
        product1.setCreatedAt(LocalDateTime.now());
        product1.setDiscountedPrice(product.getDiscountedPrice());
        product1.setQuantity(product.getQuantity());
        product1.setDiscountedPersent(product.getDiscountPersent());
        product1.setPrice(product.getPrice());
        product1.setImageUrl(product.getImageUrl());
        product1.setSizes(product.getSize());
        product1.setSubImage1(product.getSubImage1());
        product1.setSubImage2(product.getSubImage2());
        product1.setSubImage3(product.getSubImage3());
        product1.setCategory(product.getTopLevelCategory());
        return productRepository.save(product1);
    }


    @Override
    public String deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        product.getSizes().clear();
        productRepository.delete(product);
        return product + " Is Deleted";
    }

    @Override
    public Product updateProduct(Long productId, Product req) {
        Product product = productRepository.findById(productId).orElseThrow();
        product.setTitle(req.getTitle());
        product.setColor(req.getColor());
        product.setPrice(req.getPrice());
        product.setImageUrl(req.getImageUrl());
        product.setPrice(req.getPrice());
        product.setBrand(req.getBrand());
        product.setDiscountedPrice(req.getDiscountedPrice());
        product.setDescription(req.getDescription());
        product.setDiscountedPersent(req.getDiscountedPersent());
        product.setSizes(req.getSizes());
        if (req.getQuantity() != 0) {
            product.setQuantity(req.getQuantity());
        }
        return productRepository.save(product);
    }

    @Override
    public Product findByProductId(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        return product;
    }

    @Override
    public List<Product> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public List<Product> getAllProductt(List<String> color, List<String> size, Integer minPrice,
                                        Integer maxPrice, Integer minDiscount, String sort, String stock) {
        List<Product> products = productRepository.filterProductss(minPrice, maxPrice, minDiscount, sort);
        if (color != null && !color.isEmpty()) {
            products = products.stream().filter(p -> color.stream()
                    .anyMatch(c -> c.equalsIgnoreCase(p.getColor()))).collect(Collectors.toList());
        }
        if (stock != null) {
            if (stock.equals("in_stock")) {
                products = products.stream().filter(p -> p.getQuantity() > 0).collect(Collectors.toList());
            } else if (stock.equals("out_of_stock")) {
                products = products.stream().filter(p -> p.getQuantity() < 1).collect(Collectors.toList());
            }
        }
        return products;
    }
}
