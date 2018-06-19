package com.imooc.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.imooc.dataobject.OrderDetail;
import com.imooc.dto.OrderDto;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixiansheng on 2018/6/17.
 */
@Slf4j
public class OrderForm2OrderDtoConverter {

    public static OrderDto conver(OrderForm orderForm){
        OrderDto orderDto = new OrderDto();

        orderDto.setBuyerName(orderForm.getName());
        orderDto.setBuyerPhone(orderForm.getPhone());
        orderDto.setBuyerAddress(orderForm.getAddress());
        orderDto.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try{

            orderDetailList = JSONObject.parseArray(orderForm.getItems(), OrderDetail.class);
        }catch (Exception e){
            log.error("【对象转换】错误 json={}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDto.setOrderDetailList(orderDetailList);
        return  orderDto;

    }
}
