package com.start.demo.service;

import com.start.demo.bean.Stock;

public interface IStockService {

    String findStockBy(String code);

    String insertIntoDb(String key,String value);
}
