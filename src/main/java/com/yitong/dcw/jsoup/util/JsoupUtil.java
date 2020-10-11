package com.yitong.dcw.jsoup.util;

import com.google.gson.Gson;
import com.yitong.dcw.QueryAttr;
import com.yitong.dcw.UrlConstrant;
import com.yitong.dcw.entity.FocusNewsOfHC;
import com.yitong.dcw.entity.Swipe;
import lombok.Getter;
import lombok.Setter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @Classname JsoupUtil
 * @Description jsoup工具包
 * @Date 2020/9/30 19:08
 * @Created by 邓磊
 */
@Getter
@Setter
public class JsoupUtil{
    private static int timeout = 3000;
    private static volatile Map<String,String> map = new HashMap<>();
    private static volatile Map<String,String> cookies = new HashMap<>();
    private static String userAgent = "";
    private static Gson gson = new Gson();

    //使用jsoup发送get请求
    public static Document JsoupForGet(String url){
        Document document = null;

        try {
             document = Jsoup.connect(url)
                    .data(map)
                    .cookies(cookies)
                    .userAgent(userAgent)
                    .timeout(timeout)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }
    //使用jsoup发送post请求

    //寻找指定元素数组
    @Deprecated
    public static String[][] selectElements(Document document,String cssQuery, String... queryElements){
        Elements elements = document.select(cssQuery);
        String[][] elementArray = new String[elements.size()][queryElements.length];

        for(int i = 0;i < elements.size();i ++){
            for(int j = 0;j < queryElements.length;j ++){
                String element = elements.get(i).attr(queryElements[j]);
                elementArray[i][j] = element;
            }
        }

        return elementArray;
    }

    //爬取多层元素数据
    @Deprecated
    public static HashMap<String, String> selectElements(Document document, HashMap<String,ArrayList> queryAttr,String element){
        HashMap<String,String> resultHashMap = new HashMap<>();


        queryAttr.forEach((query,attr) -> {
           Elements elements = document.select(query);
           String key = null;
           String value = null;

           for(int i = 0;i < elements.size();i ++){
               if(attr.size() == 0 && null != element){
                   Elements temp = elements.get(i).select(element);
                   for(int j = 0;j <temp.size();j ++){
                       //System.out.println(temp.text());
                       resultHashMap.put("var"+i+j,temp.get(j).text());
                   }
               }else{
                   for(int j = 0;j < attr.size();j ++){
                       //System.out.println(elements.get(i).attr(attr.get(j).toString()));
                       key = attr.get(j).toString() + i;
                       value = elements.get(i).attr(attr.get(j).toString());
                       resultHashMap.put(key,value);
                   }
               }
           }
        });

        return resultHashMap;
    }

    /**
     * 多重自定义爬取元素
     * Document: document: 网页文档
     * Class: clazz: 需要强制转换的类
     * HashMap<String,ArrayList: queryAttr: 查询规则
     *      String: 定义的爬取标签
     *      ArrayList: 需要爬取的属性列表，文本用text表示
     * nameList: 自定义存放属性列表
     * */
    public static<T> ArrayList< T> selectElements(Document document,Class clazz,HashMap<String,ArrayList> queryAttr
            ,ArrayList<Integer> textLengths,String... nameList){
        //结果返回类
        ArrayList<T> resultArrayList = new ArrayList<>();
        HashMap<String,ArrayList> hashMap = new HashMap<>();
        HashMap<String,String> jsonHashMap = new HashMap<>();

        Elements elements = null;
        //nameList下标标记
        int index = 0;
        //textLength标记
        int textIndex = 0;
        //临时变量
        ArrayList<String> temp = new ArrayList<>();

        //按规则依次解析，并放入临时变量temp中，方便下一步操作
        for(String key: queryAttr.keySet()){
            elements = document.select(key);
            for(Object list : queryAttr.get(key)){
                for(int i = 0;i < elements.size();i ++){
                    if(list.toString().equals("text")){
                        temp.add(elements.get(i).text());
                    }else{
                        temp.add(elements.get(i).attr(list.toString()));
                    }
                }

                if(list.toString().equals("text")){
                    int textLength = textLengths.get(textIndex);
                    ArrayList textTemp = new ArrayList();

                    for(int i = 0;i < textLength;i ++){
                        for(int j = i;j < temp.size() - textLength;){
                            textTemp.add(temp.get(j));
                            j += textLength;
                            //System.out.println(temp.get(j));
                        }
                        hashMap.put(nameList[index],textTemp);
                        textTemp = new ArrayList();
                        index ++;
                    }
                    textIndex ++;
                }else{
                    hashMap.put(nameList[index],temp);

                    index ++;
                }
                temp = new ArrayList<>();
            }
        }

        index = 0;
        //根据分类将其按顺序添加进resultArrayList中。
        for(int i = 0;i < elements.size();i ++){
            for(int j = 0;j < nameList.length;j ++){
                String value = hashMap.get(nameList[index]).get(i).toString();
                jsonHashMap.put(nameList[index],value);
                index ++;
            }
            //转换成json数据，gson直接解析hashmap中的中文数据会出错

            String json = gson.toJson(jsonHashMap);
            //强制转换为泛型类
            T t = gson.fromJson(json, (Type)clazz);

            resultArrayList.add(t);

            index = 0;
            if(resultArrayList.size() == elements.size()-1){
                return resultArrayList;
            }
        }
        return resultArrayList;
    }


}
