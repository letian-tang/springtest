package com.zhoupu.dy;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test2 {

    public static HttpURLConnection getConnection(String httpUrl) throws Exception {
        URL url = new URL(httpUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        //connection.setInstanceFollowRedirects(true);
        //connection.setRequestProperty("Content-Type", "application/octet-stream");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        connection.setRequestProperty("Accept-Encoding", "gzip, deflate");
        connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
        connection.setRequestProperty("Cache-Control", "max-age=0");
        connection.setRequestProperty("Connection", "keep-alive");
        connection.setRequestProperty("If-Modified-Since", "Sat, 04 Nov 2017 23:05:44 GMT");
        connection.setRequestProperty("Upgrade-Insecure-Requests", "1");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
        connection.connect();
        return connection;

    }

    public static void testDownLoad() {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        String HTTP_URL = "http://img4.99114.com/group10/M00/4B/F7/rBADsln-R8iAMsFMAACzaToXr_U486.jpg"; //图片地址
        try {
            HttpURLConnection httpURLConnection = getConnection(HTTP_URL);

            if (httpURLConnection.getResponseCode() == 302 || httpURLConnection.getResponseCode() == 301) {
                //如果会重定向，保存302重定向地址，以及Cookies,然后重新发送请求(模拟请求)
                String location = httpURLConnection.getHeaderField("Location");
                httpURLConnection.disconnect();
                HTTP_URL = location;
                httpURLConnection = (HttpURLConnection) new URL(HTTP_URL).openConnection();
                httpURLConnection.setConnectTimeout(15000);
                httpURLConnection.setReadTimeout(15000);
            }
            int contentLength = getConnection(HTTP_URL).getContentLength();
            System.out.println("文件的大小是:" + contentLength);
            if (contentLength > 32) {
                InputStream is = httpURLConnection.getInputStream();
                bis = new BufferedInputStream(is);
                FileOutputStream fos = new FileOutputStream("D:/111.jpg");
                bos = new BufferedOutputStream(fos);
                int b = 0;
                byte[] byArr = new byte[1024];
                while ((b = bis.read(byArr)) != -1) {
                    bos.write(byArr, 0, b);
                }
                System.out.println("下载的文件的大小是----------------------------------------------:" + contentLength);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        testDownLoad();
    }
}
