package org.technous.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.technous.validation.model.Product;
import org.technous.validation.repository.ProductRepository;
import org.technous.validation.request.ProductRequest;
import org.technous.validation.service.impl.ProductServiceIMPL;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceIMPLTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceIMPL productService;

    @Test
    public void testAddProduct() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setTitle("Test Product");
        productRequest.setBrand("Test Brand");
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> {
            Product product = invocation.getArgument(0);
            product.setId(1L);
            return product;
        });

        Product savedProduct = productService.addProduct(productRequest);

        verify(productRepository, times(1)).save(any(Product.class));

        assertEquals("Test Product", savedProduct.getTitle());
        assertEquals("Test Brand", savedProduct.getBrand());
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product();
        product.setId(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        String result = productService.deleteProduct(1L);

        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).delete(any(Product.class));

        assertEquals("Product{id=1, title='null', ...} Is Deleted", result);
    }

}
