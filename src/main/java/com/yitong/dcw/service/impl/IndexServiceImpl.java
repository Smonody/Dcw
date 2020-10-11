package com.yitong.dcw.service.impl;

import com.yitong.dcw.QueryAttr;
import com.yitong.dcw.UrlConstrant;
import com.yitong.dcw.VO.Response;
import com.yitong.dcw.VO.ResponseResult;
import com.yitong.dcw.entity.CountrySite;
import com.yitong.dcw.entity.FocusNewsOfHC;
import com.yitong.dcw.entity.Swipe;
import com.yitong.dcw.jsoup.util.JsoupUtil;
import com.yitong.dcw.service.IndexService;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Classname IndexServiceImpl
 * @Description TODO
 * @Date 2020/10/1 23:00
 * @Created by 邓磊
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Override
    public ResponseResult<ArrayList<Swipe>> getSwipe() {
        //swipe组件数据
        Document document = JsoupUtil.JsoupForGet(UrlConstrant.baseUrl);
        HashMap<String, ArrayList> queryElements = new HashMap<>();

        ArrayList<String> query = new ArrayList<>();
        query.add(QueryAttr.src);
        queryElements.put("ul.items li a img",query);

        query = new ArrayList<>();

        query.add(QueryAttr.title);
        query.add(QueryAttr.href);
        queryElements.put("ul.items li a",query);

        ArrayList<Integer> textLengths = new ArrayList<>();
        textLengths.add(0);

        try{
            ArrayList<Swipe> arrayList = JsoupUtil.selectElements(document,Swipe.class,queryElements,textLengths,"src","title","url");
            return Response.makeOKRsp(arrayList);
        }catch (Exception e){
            return Response.makeErrRsp("请求错误，请稍后再试");
        }
    }

    @Override
    public ResponseResult<ArrayList<FocusNewsOfHC>> getHcyw() {
        //合川要闻数据
        Document hcyw = JsoupUtil.JsoupForGet(UrlConstrant.hcyw);
        HashMap<String,ArrayList> hcywHashMap = new HashMap<>();
        ArrayList<String> hcywQuery = new ArrayList<>();

        hcywQuery.add(QueryAttr.href);
        hcywHashMap.put("div.lbbox_n ul li a",hcywQuery);

        hcywQuery = new ArrayList<>();
        hcywQuery.add(QueryAttr.src);
        hcywHashMap.put("div.lbbox_n ul li a img",hcywQuery);

        hcywQuery = new ArrayList<>();
        hcywQuery.add(QueryAttr.text);
        hcywHashMap.put("div.lbbox_n ul li a span",hcywQuery);

        ArrayList<FocusNewsOfHC> arrayListNews = null;

        ArrayList<Integer> textLengths = new ArrayList<>();
        textLengths.add(2);

        try {
            arrayListNews = JsoupUtil.selectElements(hcyw, FocusNewsOfHC.class,hcywHashMap,textLengths,QueryAttr.title,QueryAttr.date,QueryAttr.url,QueryAttr.src);

            return Response.makeOKRsp(arrayListNews);
        }catch (Exception e){
            return Response.makeErrRsp(e.getMessage());
        }


    }

    @Override
    public ResponseResult<ArrayList<CountrySite>> getCountrySite() {
        Document document = JsoupUtil.JsoupForGet(UrlConstrant.baseUrl);

        HashMap<String,ArrayList> hashMap = new HashMap<>();
        ArrayList<String> queryList = new ArrayList<>();

        String queryStr = "ul.yb-drop-lists li a";
        queryList.add(QueryAttr.href);
        queryList.add(QueryAttr.title);

        ArrayList<Integer> textLengths = new ArrayList<>();
        textLengths.add(0);

        hashMap.put(queryStr,queryList);

        ArrayList<CountrySite> countrySites = JsoupUtil.selectElements(document, CountrySite.class,hashMap,textLengths,"url","title");
        return Response.makeOKRsp(countrySites);
    }

}
