package com.imooc.controller;

import com.imooc.converter.OrderForm2OrderDtoConverter;
import com.imooc.dto.OrderDto;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.form.OrderForm;
import com.imooc.service.BuyerService;
import com.imooc.service.OrderService;
import com.imooc.utils.ResultVOUtil;
import com.imooc.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lixiansheng on 2018/6/17.
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {


    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确,orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDto orderDto = OrderForm2OrderDtoConverter.conver(orderForm);
        if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDto createResult = orderService.create(orderDto);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", orderDto.getOrderId());
        return ResultVOUtil.success(map);

    }

    //订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDto>> list(@RequestParam("openid") String opendid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(opendid)) {
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest request = new PageRequest(page, size);
        Page<OrderDto> orderDtoPage = orderService.findList(opendid, request);
        return ResultVOUtil.success(orderDtoPage.getContent());
    }

    //订单详情
    @GetMapping("/detail")
    public ResultVO<OrderDto> detail(@RequestParam("openid") String openid,

                                     @RequestParam("orderId") String orderId) {
        //TODO 不安全 改进
        OrderDto orderDto = buyerService.findOrderOne(openid,orderId);
        return ResultVOUtil.success(orderDto);
    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVO<OrderDto> cancel(@RequestParam("openid") String openid,

                                     @RequestParam("orderId") String orderId) {
        buyerService.cancelOrder(openid,orderId);
        return ResultVOUtil.success();
    }
}