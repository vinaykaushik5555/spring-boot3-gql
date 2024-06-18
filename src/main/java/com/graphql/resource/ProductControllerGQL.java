package com.graphql.resource;

import com.graphql.entity.Product;
import com.graphql.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class ProductControllerGQL {

    private final ProductRepository productRepository;

    @QueryMapping
    public List<Product> products() {
        return productRepository.findAll();
    }

    @QueryMapping
    public Product product(@Argument Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public List<Product> productsByCategory(@Argument String category) {
        return productRepository.findByCategory(category);
    }
}
