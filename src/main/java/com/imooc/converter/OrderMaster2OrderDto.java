package com.imooc.converter;

import com.imooc.dataobject.OrderMaster;
import com.imooc.dto.OrderDto;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by lixiansheng on 2018/6/17*/
public class OrderMaster2OrderDto {
    public static OrderDto convert(OrderMaster orderMaster){
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        return orderDto;
    }


    public static List<OrderDto> convert(List<OrderMaster> orderMasterList){
       return  orderMasterList.stream().map(e ->
            convert(e)
        ).collect(Collectors.toList());
    }
}




