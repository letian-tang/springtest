package com.zhoupu;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Test3 {

    public static void main(String[] args) {
        CloseableHttpClient client = null;
        try {
            client = HttpClients.createDefault();
            String url = "http://www.cdkjbg.com/images/201306/goods_img/2451_G_1371878929404.jpg";

            httpGetImg(client, url , "D:/11/img/ss.jpg");
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e2) {

                }
            }
        }

    }


    public static void httpGetImg(CloseableHttpClient client, String imgUrl, String savePath) {

// 发送get请求
        HttpGet request = new HttpGet(imgUrl);
// 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000).build();

// 设置请求头
        request.setHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.79 Safari/537.1");

        request.setConfig(requestConfig);
        try {
            CloseableHttpResponse response = client.execute(request);

            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                HttpEntity entity = response.getEntity();
                InputStream in = entity.getContent();
                FileUtils.copyInputStreamToFile(in, new File(savePath));
                System.out.println("下载图片成功:" + imgUrl);
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            request.releaseConnection();
        }
    }
}
