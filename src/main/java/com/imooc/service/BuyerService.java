package com.imooc.service;

import com.imooc.dto.OrderDto;

/**
 * Created by lixiansheng on 2018/6/17.
 */
public interface BuyerService {

    //查询一个订单
    OrderDto findOrderOne(String openid,String orderId);

    //取消订单
    OrderDto cancelOrder(String openid,String orderId);
}
