package com.imooc.dto;

import lombok.Data;

/**
 * Created by lixiansheng on 2018/6/17.
 */
@Data
public class CarDTO {

    /** 商品 ID*/
    private String productId;

    /** 数量 */
    private Integer productQuantity;

    public CarDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
