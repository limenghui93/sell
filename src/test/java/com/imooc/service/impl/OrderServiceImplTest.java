package com.imooc.service.impl;

import com.imooc.dataobject.OrderDetail;
import com.imooc.dto.OrderDto;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import com.imooc.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by lixiansheng on 2018/6/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    private final String BUYEROPENID = "110110";

    private  final String ORDERID = "1529236999707348345";

    @Test
    public void create() throws Exception {
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName("李孟辉");
        orderDto.setBuyerAddress("慕课网");
        orderDto.setBuyerPhone("123456789090");
        orderDto.setBuyerOpenid(BUYEROPENID);
        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setProductId("123456");
        orderDetail1.setProductQuantity(1);
        orderDetailList.add(orderDetail1);


        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setProductId("123457");
        orderDetail2.setProductQuantity(1);
        orderDetailList.add(orderDetail2);

        orderDto.setOrderDetailList(orderDetailList);

        OrderDto result = orderService.create(orderDto);
        log.info("【创建订单】 result={}",result);
        Assert.assertNotNull(result);


    }

    @Test
    public void findOne() throws Exception {
        OrderDto ressult = orderService.findOne(ORDERID);
        log.info("【查询单个订单】 result={}",ressult);
        Assert.assertNotNull(ressult);
    }

    @Test
    public void findList() throws Exception {
        PageRequest request = new PageRequest(0,5);
        Page<OrderDto> orderDtoPage = orderService.findList(BUYEROPENID,request);
        Assert.assertNotEquals(0,orderDtoPage.getTotalElements());
    }

    @Test
    public void cancel() throws Exception {
        OrderDto orderDto = orderService.findOne(ORDERID);
        OrderDto result = orderService.cancel(orderDto);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());
    }

    @Test
    public void finish() throws Exception {
        OrderDto orderDto = orderService.findOne(ORDERID);
        OrderDto result = orderService.finish(orderDto);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),result.getOrderStatus());

    }

    @Test
    public void paid() throws Exception {
        OrderDto orderDto = orderService.findOne(ORDERID);
        OrderDto result = orderService.paid(orderDto);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),result.getPayStatus());

    }

}