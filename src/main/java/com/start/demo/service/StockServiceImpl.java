package com.start.demo.service;

import com.start.demo.bean.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class StockServiceImpl implements IStockService {


    @Autowired
    private RedisTemplate redisTemplate;



    @Override
    public String findStockBy(String code) {

        System.out.println(code);
        ValueOperations<String,String> operations = redisTemplate.opsForValue();
            boolean hasKey = redisTemplate.hasKey(code);

            String stock = operations.get(code);
            System.out.println("----find cache use here---" + stock.toString());
            return stock;


        //历史行情从数据库查

    }

    @Override
    public String insertIntoDb(String key, String value) {

        ValueOperations<String,String> operations = redisTemplate.opsForValue();
        /**code：代码
        name:名称
        changepercent:涨跌幅
        trade:现价
        open:开盘价
        high:最高价
        low:最低价
        settlement:昨日收盘价
        volume:成交量
        turnoverratio:换手率
        amount:成交量
        per:市盈率
        pb:市净率
        mktcap:总市值
        nmc:流通市值
         **/
       // String stockValue = "{code:60002,open:70.1,high:72.1,value:100,trade:71.1,low:69.9,time:20171219930,settlement:}";
        operations.set(key,value );
        return value;
    }
}
