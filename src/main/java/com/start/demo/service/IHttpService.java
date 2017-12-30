package com.start.demo.service;

import com.start.demo.bean.HttpResult;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public interface IHttpService {

    String doGet(String url ) throws IOException;

    String doGet(String url,Map<String, Object> map) throws URISyntaxException, IOException;

    String doPost(String url);

    HttpResult doPost(String url, Map<String, Object> map) throws IOException;
}
