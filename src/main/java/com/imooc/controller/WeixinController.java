package com.imooc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by lixiansheng on 2018/6/18.
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {

    @GetMapping("auth")
    public void auth(@RequestParam("code") String code){
        log.info("进入auth方法。。。code={}",code);

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx224b1d069e63b213&secret=ae5f36ec231d7044569859bba5e7e566&code="+code+"&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url,String.class);
        log.info("resposne={}",response);

    }
}
