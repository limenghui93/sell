package com.imooc.enums;

import lombok.Getter;

/**
 * Created by lixiansheng on 2018/6/17.
 */
@Getter
public enum ProductStatusEnum {
    UP(0,"上架"),
    DOWN(1,"下架");

    private Integer code;

    private String message;
    ProductStatusEnum(Integer code,String message) {
        this.code = code;
        this.message = message;
    }
}
