package com.example.Product.delivery.service;

import com.example.Product.delivery.domain.Product;
import com.example.Product.delivery.dto.ProductDTO;
import com.example.Product.delivery.mapper.ProductMapper;
import com.example.Product.delivery.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<ProductDTO> getProductById(Long id) {
        return productRepository.findById(id)
                .map(ProductMapper::toProductDTO);
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = ProductMapper.toProduct(productDTO);
        return ProductMapper.toProductDTO(productRepository.save(product));
    }

    public ProductDTO updateProduct(Long id, ProductDTO updatedProductDTO) {
        return productRepository.findById(id)
                .map(product -> {
                    updatedProductDTO.setId(id);
                    Product updatedProduct = ProductMapper.toProduct(updatedProductDTO);
                    return ProductMapper.toProductDTO(productRepository.save(updatedProduct));
                })
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }


    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

