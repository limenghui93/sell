package com.imooc.repository;

import com.imooc.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by lixiansheng on 2018/6/16.
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer>{
    List<ProductCategory>  findByCategoryTypeIn(List<Integer> categoryTypeList);
}
