package com.start.demo.service;

import com.start.demo.bean.HttpResult;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class HttpServiceImpl implements IHttpService{

    @Autowired
    private CloseableHttpClient httpClient;

    @Autowired
    private RequestConfig requestConfig;

    private static final String ACCESS_TOKEN = "bbfaca3a9fe22ddde742c0172e154ea07b933e5560bce51759e17bceb82af0e5";
    @Override
    public String doGet(String url) throws IOException {

        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Authorization", "Bearer " + ACCESS_TOKEN);
        httpGet.setConfig(requestConfig);
        Header[] headers = httpGet.getAllHeaders();
        for(Header header:headers){
            System.out.println(header.getName()+":"+header.getValue());
        }
        CloseableHttpResponse response = this.httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == 200){
            return EntityUtils.toString(response.getEntity(),"UTF-8");
        }
        return null;
    }

    @Override
    public String doGet(String url, Map<String, Object> map) throws URISyntaxException, IOException {
        URIBuilder uriBuilder = new  URIBuilder(url);
        if(map != null){
            for(Map.Entry<String,Object> entry:map.entrySet()){
                uriBuilder.setParameter(entry.getKey(),entry.getValue().toString());
            }
        }

        return this.doGet(uriBuilder.build().toString());
    }

    @Override
    public String doPost(String url) {


        return null;
    }

    @Override
    public HttpResult doPost(String url, Map<String, Object> map) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);

        if (map !=null){
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            for(Map.Entry<String,Object> entry: map.entrySet()){
                list.add(new BasicNameValuePair(entry.getKey(),entry.getValue().toString()));
            }

            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list,"UTF-8");
            httpPost.setEntity(urlEncodedFormEntity);
        }
        CloseableHttpResponse response = httpClient.execute(httpPost);

        return new HttpResult(response.getStatusLine().getStatusCode(),EntityUtils.toString(response.getEntity()),"UTF-8");
     }
    }
