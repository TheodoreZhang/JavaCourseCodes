package com.server;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class ServerClient {

    public static void main(String[] args) throws IOException {
        //1.获得一个httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //2.生成一个get请求
        HttpGet httpGet = new HttpGet("http://localhost:8801");
        //3.获取响应
        CloseableHttpResponse response = httpclient.execute(httpGet);
    }

}