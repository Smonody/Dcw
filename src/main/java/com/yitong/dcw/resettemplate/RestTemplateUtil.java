package com.yitong.dcw.resettemplate;

import com.google.gson.Gson;
import com.yitong.dcw.UrlConstrant;
import com.yitong.dcw.jsoup.util.JsoupUtil;
import com.yitong.dcw.service.IndexService;
import com.yitong.dcw.service.impl.IndexServiceImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * @Classname RestTemplate
 * @Description TODO
 * @Date 2020/9/30 20:10
 * @Created by 邓磊
 */
public class RestTemplateUtil<T> {

    private static RestTemplate restTemplate = new RestTemplate();

    //get请求
    public static<T> Object getRest(String url,Class<T> t) throws UnsupportedEncodingException {
        // 添加header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        // 添加请求参数
        MultiValueMap params = new LinkedMultiValueMap();
        // params.add("test", "");
        HttpEntity requestBody = new HttpEntity(params, headers);
        String html;
        ResponseEntity<T> responseEntity = (ResponseEntity<T>) restTemplate.getForEntity(url,String.class);
        //String seqResult = new String(responseEntity.getBody().getBytes("ISO8859-1"),"utf-8");
        return responseEntity.toString();
    }


    public static Jsoup jsoup;

    public static void main(String[] args) throws UnsupportedEncodingException {
        RestTemplateUtil restTemplateUtil = new RestTemplateUtil();


    }
}
