package com.imooc.controller;

import com.imooc.dto.OrderDto;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by lixiansheng on 2018/6/18.
 */
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/create")
    public void create(@RequestParam("orderId") String orderId,
                       @RequestParam("returnUrl")String returnUrl){
        //1 查询订单
        OrderDto orderDto = orderService.findOne(orderId);
        if(orderDto == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //2 发起支付
    }

}
