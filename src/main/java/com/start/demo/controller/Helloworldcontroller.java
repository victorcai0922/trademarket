package com.start.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.start.demo.service.IHttpService;
import com.start.demo.service.IStockService;
import com.start.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.List;

@RestController
public class Helloworldcontroller {

    @Autowired
    private IUserService userService;

    @Autowired
    private IStockService stockService;

    @Autowired
    private IHttpService httpService;

    @RequestMapping("/hello")
    public String index() {
        return  "Hello world!";
    }

    @RequestMapping("/user/{name}")
    public String getUser(@PathVariable("user") String name) {
        return userService.findUserby(name).getName();
    }

    @RequestMapping("/{code}")
    public String getStock(@PathVariable String code){
        return stockService.findStockBy(code).toString();
    }

    @RequestMapping("/stock/{code}")
    public String insert(@PathVariable("code") String code){
        return stockService.insertIntoDb(code,"xxx");
    }


    @RequestMapping("/httpclient")
    public String doGet(){

        String str = null;
        try{
            str = httpService.doGet("https://api.wmcloud.com/data/v1//api/equity/getMktEqudCCXE.json?field=&secID=000001.XSHE&startDate=&endDate=");
//            syso
//            JSONObject response = new JSONObject(str);
            JSONObject response =  JSON.parseObject(str);
            //response.get("data");
            JSONArray arrays = JSON.parseArray(response.get("data").toString());

            for (int i=0; i<arrays.size(); i++){
                String stock = arrays.get(i).toString();
                System.out.println(stock);
                JSONObject stockObject = JSON.parseObject(stock);
                String code = stockObject.getString("ticker");
                String tradedate = stockObject.getString("tradeDate");
                StringBuilder stringBuilder = new StringBuilder(code);
                stringBuilder.append("_").append(tradedate);
                String key = stringBuilder.toString();
                String key2 = key.replaceAll(" ","");
                System.out.println("key:"+key2);
                stockService.insertIntoDb(key2,stock);
            }

            //System.out.println(response.get("data"));
        }catch (Exception exception){

        }

         return str;
            }



}
