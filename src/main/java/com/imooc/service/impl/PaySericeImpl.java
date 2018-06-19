package com.imooc.service.impl;

import com.imooc.dto.OrderDto;
import com.imooc.service.PayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Created by lixiansheng on 2018/6/18.
 */
@Service
public class PaySericeImpl implements PayService {

    @Override
    public void create(OrderDto orderDto) {
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();

    }
}
