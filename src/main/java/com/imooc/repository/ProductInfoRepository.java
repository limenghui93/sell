package com.imooc.repository;

import com.imooc.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by lixiansheng on 2018/6/17.
 */
public interface  ProductInfoRepository extends JpaRepository<ProductInfo,java.lang.String> {
   public List<ProductInfo> findByProductStatus(Integer productStatus);
}
