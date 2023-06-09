package com.egrikulas.pinsoft.product.impl;


import com.egrikulas.pinsoft.library.rest.BaseController;

import com.egrikulas.pinsoft.library.rest.MetaResponse;
import com.egrikulas.pinsoft.library.rest.PageResponse;
import com.egrikulas.pinsoft.library.rest.Response;
import com.egrikulas.pinsoft.library.util.PageUtil;
import com.egrikulas.pinsoft.product.api.ProductDto;
import com.egrikulas.pinsoft.product.api.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController extends BaseController {

    private final ProductService service;

    @PostMapping("/save-all")
    public Response<ProductResponse> saveAllProduct(@RequestBody List<ProductDto> dtos){
    service.saveAllProduct(dtos);
    return new Response<>(MetaResponse.ofSuccess());
    }


    @PostMapping
    public Response<ProductResponse> createProduct(@Valid @RequestBody ProductRequest request){
        var product = service.createProduct(request.toDto());
        return respond(ProductResponse.fromDto(product));
    }
    @PutMapping("/{id}")
    public Response<ProductResponse> updateProduct(@PathVariable(value = "id")String id,
                                                         @RequestBody ProductRequest request){
        ProductDto dto = service.updateProduct(id, request.toDto());
        return respond(ProductResponse.fromDto(dto));
    }
    @DeleteMapping("/{id}")
    public Response<Void> deleteProduct(@PathVariable(value = "id") String id){
        service.deleteProduct(id);
        return new Response<>(MetaResponse.ofSuccess());
    }
    @GetMapping
    public Response<PageResponse<ProductResponse>>getAllProduct(Pageable pageable){
        return respond(toPageResponse(service.getAllProductPageable(pageable)));
    }

    @GetMapping("/filter")
    public Response<PageResponse<ProductResponse>> filterProduct(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String category,
            Pageable pageable){
        ProductDto dto = ProductDto.builder()
                .title(title)
                .price(price)
                .description(description)
                .category(category)
                .build();
        return  respond(toPageResponse(service.filterProduct(dto, pageable)));

    }

    private Page<ProductResponse> toPageResponse(Page<ProductDto> productDtos){
        return PageUtil.pageToDto(productDtos, ProductResponse::fromDto);
    }

    @GetMapping("/{id}")
    public Response<ProductResponse> getProductById(@PathVariable(value = "id") String id){
       return respond( ProductResponse.fromDto(service.getProductById(id)));
    }


}
