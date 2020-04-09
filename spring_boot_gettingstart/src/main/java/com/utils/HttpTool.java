package com.utils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;


public class HttpTool {

    // private static final Logger LOGGER = Logger.getLogger(HttpTool.class);

    /**
     * ????post????
     * 
     * @param params
     *            ????
     * @param requestUrl
     *            ??????
     * @param authorization
     *            ????��??????????????????????
     * @return ??????
     * @throws IOException
     */
    public static String sendPost(String params, String requestUrl,
            String authorization) throws IOException {
        String ContentType = "application/x-www-form-urlencoded";
        byte[] requestBytes = params.getBytes("gbk"); // ????????????????
        // for (int j = 0; j < requestBytes.length; ++j) {  
        //     if (requestBytes[j] < 0) {// ??????????  
        //         requestBytes[j] += 256;  
        //     }  
        // }
        HttpClient httpClient = new HttpClient();// ??????????
        PostMethod postMethod = new PostMethod(requestUrl);
        //?????????Authorization??User-Agent??Accept
        postMethod.setRequestHeader("Authorization", "Basic " + authorization);
        postMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36");
        postMethod.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        // ?????????  Content-Type
        postMethod.setRequestHeader("Content-Type", ContentType);
        InputStream inputStream = new ByteArrayInputStream(requestBytes, 0,
                requestBytes.length);
        RequestEntity requestEntity = new InputStreamRequestEntity(inputStream,
                requestBytes.length, "application/json; charset=gbk"); // ??????
        postMethod.setRequestEntity(requestEntity);
        httpClient.executeMethod(postMethod);// ???????
        InputStream soapResponseStream = postMethod.getResponseBodyAsStream();// ??????????
        byte[] datas = null;
        try {
            datas = readInputStream(soapResponseStream);// ?????????��??????
        } catch (Exception e) {
            // System.out.println(new Date()+"|sendPost??|ERROR|"+e.toString()+CommonUtils.errorTrackSpace(e));
            // LOGGER.error("sendPost??|"+e.toString()+CommonUtils.errorTrackSpace(e));
            e.printStackTrace();
        }
        String result = new String(datas, "GBK");// ????????????String
        
        return result;
    }

    /**
     * ?????????��??????
     * 
     * @param inStream
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;
    }
    
}