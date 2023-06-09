package com.egrikulas.pinsoft.product.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductDto createProduct(ProductDto dto);

    ProductDto updateProduct(String id, ProductDto dto);

    void deleteProduct(String id);

    Page<ProductDto> getAllProductPageable(Pageable pageable);

    Page<ProductDto> filterProduct(ProductDto productDto, Pageable pageable);

    void saveAllProduct(List<ProductDto> dtos);

    ProductDto getProductById(String id);
}
