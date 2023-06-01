package mx.uv.shoppincard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uv.shoppincard.entity.Product;
import mx.uv.shoppincard.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }
}