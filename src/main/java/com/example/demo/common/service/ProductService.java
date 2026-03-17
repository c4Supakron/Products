package com.example.demo.common.service;


import com.example.demo.common.dto.Product;
import com.example.demo.common.dto.ProductRes;
import com.example.demo.common.entity.ProductEntity;
import com.example.demo.common.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {this.productRepository = productRepository;}

    public void saveProduct(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProduct_name(product.getProduct_name());
        productEntity.setProduct_code(product.getProduct_code());
        productEntity.setCategory_id(product.getCategory_id());
        productEntity.setSupplier_id(product.getSupplier_id());
        productEntity.setPrice(product.getPrice());
        productRepository.save(productEntity);

    }

    public List<ProductRes> findAll() {
        List<ProductRes> productResList = new ArrayList<>();
        List<ProductEntity> productEntitys = (List<ProductEntity>) productRepository.findAll();
        for(int i = 0; i < productEntitys.size();i++) {
            ProductRes productRes = new ProductRes();
            productRes.setProduct_name(productEntitys.get(i).getProduct_name());
            productRes.setProduct_code(productEntitys.get(i).getProduct_code());
            productRes.setCategory_id(productEntitys.get(i).getCategory_id());
            productRes.setSupplier_id(productEntitys.get(i).getSupplier_id());
            productRes.setPrice(productEntitys.get(i).getPrice());
            productResList.add(productRes);
        }
        return productResList;
    }

    public ProductRes findById(Long id) {

        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found id = " + id));

        ProductRes res = new ProductRes();
        res.setProduct_name(entity.getProduct_name());
        res.setProduct_code(entity.getProduct_code());
        res.setCategory_id(entity.getCategory_id());
        res.setSupplier_id(entity.getSupplier_id());
        res.setPrice(entity.getPrice());

        return res;
    }

}
