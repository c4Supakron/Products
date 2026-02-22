package com.example.demo.product.service;

import com.example.demo.product.dto.BuyReq; // เพิ่ม import
import com.example.demo.product.dto.BuyRes; // เพิ่ม import
import com.example.demo.product.dto.CategoriesReq;
import com.example.demo.product.dto.ProductsReq;
import com.example.demo.product.entity.CategoriesEntity;
import com.example.demo.product.entity.ProductsEntity;
import com.example.demo.product.repository.CategoriesRepository;
import com.example.demo.product.repository.ProductsRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal; // เพิ่ม import
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsService {

    private final ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }


    public void saveProducts(ProductsReq productsReq){
        ProductsEntity productsEntity = new ProductsEntity();
        productsEntity.setCategory_id(productsReq.getCategory_id());
        productsEntity.setName(productsReq.getName());
        productsEntity.setDescription(productsReq.getDescription());
        productsEntity.setPrice(productsReq.getPrice());
        productsEntity.setStock(productsReq.getStock());
        productsRepository.save(productsEntity);
    }

    public List<ProductsReq> findall() {
        List<ProductsReq> productsRequestList = new ArrayList<>();
        List<ProductsEntity> productsEntities = (List<ProductsEntity>) productsRepository.findAll();


        for (int i = 0; i < productsEntities.size(); i++){
            ProductsReq productsReq = new ProductsReq();
            productsReq.setId(productsEntities.get(i).getId());
            productsReq.setCategory_id(productsEntities.get(i).getCategory_id());
            productsReq.setName(productsEntities.get(i).getName());
            productsReq.setDescription(productsEntities.get(i).getDescription());
            productsReq.setPrice(productsEntities.get(i).getPrice());
            productsReq.setStock(productsEntities.get(i).getStock());
            productsRequestList.add(productsReq);

        }
        return productsRequestList;
    }


    public BuyRes buyProduct(BuyReq buyreq) {
        BuyRes response = new BuyRes();

        ProductsEntity product = productsRepository.findById(buyreq.getProductId())
                .orElseThrow(() -> new RuntimeException("ไม่พบสินค้า ID: " + buyreq.getProductId()));

        if (product.getStock() < buyreq.getQuantity()) {
            throw new RuntimeException("สินค้าไม่เพียงพอ (เหลือ " + product.getStock() + " ชิ้น)");
        }

        BigDecimal totalPrice = product.getPrice().multiply(new BigDecimal(buyreq.getQuantity()));

        if (buyreq.getMoney().compareTo(totalPrice) < 0) {
            throw new RuntimeException("เงินไม่พอชำระ (ราคารวม: " + totalPrice + " บาท)");
        }

        product.setStock(product.getStock() - buyreq.getQuantity());
        productsRepository.save(product);

        // 🌟 คำนวณเงินทอน (เงินที่รับมา - ราคารวม)
        BigDecimal change = buyreq.getMoney().subtract(totalPrice);

        response.setMessage("ชำระเงินสำเร็จ! ซื้อ " + product.getName() + " จำนวน " + buyreq.getQuantity() + " ชิ้น");
        response.setTotalPrice(totalPrice);
        response.setRemainingStock(product.getStock());
        response.setChange(change); // <--- 🌟 ส่งเงินทอนกลับไป

        return response;
    }

}