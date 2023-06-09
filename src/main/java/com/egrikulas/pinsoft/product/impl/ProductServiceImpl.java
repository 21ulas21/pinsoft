package com.egrikulas.pinsoft.product.impl;

import com.egrikulas.pinsoft.library.util.PageUtil;
import com.egrikulas.pinsoft.product.api.ProductDto;
import com.egrikulas.pinsoft.product.api.ProductService;
import com.egrikulas.pinsoft.rating.impl.RatingServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private  final ProductRepository repository;
    private final RatingServiceImpl ratingService;
    @Override
    public ProductDto createProduct(ProductDto dto) {
        Product product = toEntity(new Product(), dto);
        return toDto(repository.save(product));
    }

    @Override
    @Transactional
    public ProductDto updateProduct(String id, ProductDto dto) {
        return repository.findById(id)
                .map(product -> toEntity(product, dto))
                .map(repository::save)
                .map(this::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void deleteProduct(String id) {
        repository.delete(repository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public Page<ProductDto> getAllProductPageable(Pageable pageable) {
        return PageUtil.pageToDto(repository.findAll(pageable), this::toDto);
    }

    @Override
    public Page<ProductDto> filterProduct(ProductDto productDto, Pageable pageable) {
        Product product = toEntity(new Product(), productDto);
        Example<Product> productExample = Example.of(product);
        System.out.println(product);
        System.out.println(repository.findAll(productExample));
        return PageUtil.pageToDto(repository.findAll(Example.of(toEntity(new Product(), productDto)), pageable), this::toDto);
    }

    @Override
    public void saveAllProduct(List<ProductDto> dtos) {

        dtos.stream()
                .map(dto -> {
                    Product product = toEntity(new Product(), dto);
                    product.setRating(ratingService.saveRating(dto.getRating()));
                    return product;
                })
                .forEach(repository::save);

    }

    @Override
    public ProductDto getProductById(String id) {
        return toDto(repository.findById(id).orElseThrow(()-> new EntityNotFoundException("Kayıt bulunamadı")));
    }


    public Product toEntity(Product product, ProductDto dto){
        product.setCategory(dto.getCategory());
        product.setDescription(dto.getDescription());
        product.setImage(dto.getImage());
        product.setPrice(dto.getPrice());
        product.setTitle(dto.getTitle());
        product.setRating(dto.getRating().getId()==null ? null : ratingService.getRatingById(dto.getRating().getId()));
        return product;
    }
    public ProductDto toDto(Product product){
        return ProductDto.builder()
                .id(product.getId())
                .created(product.getCreated())
                .modified(product.getModified())
                .category(product.getCategory())
                .description(product.getDescription())
                .price(product.getPrice())
                .title(product.getTitle())
                .image(product.getImage())
                .rating(ratingService.toDto(product.getRating()))
                .build();
    }

}
