package com.ds.myapp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 公共工具类
 * Created by xxxxx on 2016/10/20.
 */
public class CommonUtils {

    private InputStream inputStream;
    private HttpURLConnection conn;
    private StringBuilder sb;

    public String httpUrlConnect(String urlPath){
        sb = new StringBuilder();

        URL url = null;
        try {
            url = new URL(urlPath);
            //打开连接
            conn = (HttpURLConnection) url.openConnection();
            if(conn.getResponseCode() == 200) {
                inputStream = conn.getInputStream();
                byte[] b = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(b)) != -1) {
                    sb.append(new String(b, 0, len));
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            conn.disconnect();
        }
        return sb.toString();
    }
}
