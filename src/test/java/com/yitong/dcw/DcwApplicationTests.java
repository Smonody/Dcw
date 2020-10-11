package com.yitong.dcw;

import com.google.gson.Gson;
import com.yitong.dcw.entity.CountrySite;
import com.yitong.dcw.entity.FocusNewsOfHC;
import com.yitong.dcw.service.IndexService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DcwApplicationTests {

    @Resource
    private IndexService indexService;

    Gson gson = new Gson();
    @Test
    void contextLoads() {
        System.out.println(indexService.getSwipe().getData());

        System.out.println(indexService.getHcyw().getData());

        //获取国家服务网站
        System.out.println(indexService.getCountrySite().getData());
    }



}
