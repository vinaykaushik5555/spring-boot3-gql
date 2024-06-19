package com.graphql.resource;

import com.graphql.entity.Product;
import com.graphql.repository.ProductRepository;
import com.graphql.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.Arguments;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class ProductControllerGQL {

    private final ProductService productService;

    @QueryMapping
    public List<Product> products() {
        return productService.getAllProducts();
    }

    @QueryMapping
    public Product product(@Argument Long id) {
        return productService.getProductById(id).orElse(null);
    }

    @QueryMapping
    public List<Product> productsByCategory(@Argument String category) {
        return productService.getProductsByCategory(category);
    }

    @MutationMapping
    public Product createProduct(@Argument String name, @Argument String description, @Argument Float price, @Argument Integer stock, @Argument String category, @Argument String manufacturer) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(BigDecimal.valueOf(price));
        product.setStock(stock);
        product.setCategory(category);
        product.setManufacturer(manufacturer);
        return productService.createProduct(product);
    }

    @MutationMapping
    public Product updateStock(@Argument Long id, @Argument Integer stock) {
        return productService.updateStock(id, stock);
    }
}
