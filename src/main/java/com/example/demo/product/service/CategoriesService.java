package com.example.demo.product.service;

import com.example.demo.product.dto.CategoriesReq;
import com.example.demo.product.entity.CategoriesEntity;
import com.example.demo.product.repository.CategoriesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;
    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public void saveCategories(CategoriesReq categoriesReq){
        CategoriesEntity categoriesEntity = new CategoriesEntity();
        categoriesEntity.setName(categoriesReq.getName());
        categoriesEntity.setDescription(categoriesReq.getDescription());
        categoriesRepository.save(categoriesEntity);
    }

    public List<CategoriesReq> findall() {
        List<CategoriesReq> categoryResList = new ArrayList<> ();
        List<CategoriesEntity> categoriesEntities = (List<CategoriesEntity>) categoriesRepository.findAll();


        for (int i = 0; i < categoriesEntities.size(); i++){
            CategoriesReq categoryRes = new CategoriesReq();
            categoryRes.setId(categoriesEntities.get(i).getId());
            categoryRes.setName(categoriesEntities.get(i).getName());
            categoryRes.setDescription(categoriesEntities.get(i).getDescription());
            categoryResList.add(categoryRes);

        }
        return categoryResList;
    }
}
