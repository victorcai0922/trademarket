package com.start.demo.controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarketDataController {

    @RequestMapping("/market/{code}")
    public String getDaliyMarketdata(@PathVariable("code") String code){
    // https://api.wmcloud.com/data/v1//api/market/getMktEqud.json?field=&beginDate=&endDate=&secID=&ticker=&tradeDate=20150513
        return null;
    }
}
