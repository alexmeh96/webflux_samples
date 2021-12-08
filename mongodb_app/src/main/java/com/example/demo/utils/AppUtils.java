package com.example.demo.utils;

import com.example.demo.dto.ProductDto;
import com.example.demo.model.Product;
import org.springframework.beans.BeanUtils;

public class AppUtils {

    public static ProductDto modelToDto(Product product) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }

    public static Product dtoToModel(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }
}
