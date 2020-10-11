package com.yitong.dcw.controller;

import com.yitong.dcw.VO.ResponseResult;
import com.yitong.dcw.entity.CountrySite;
import com.yitong.dcw.entity.FocusNewsOfHC;
import com.yitong.dcw.entity.Swipe;
import com.yitong.dcw.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @Classname indexController
 * @Description TODO
 * @Date 2020/10/11 16:13
 * @Created by 邓磊
 */
@RestController
@RequestMapping("/miniApp")
public class indexController {
    @Autowired
    private IndexService indexService;

    @GetMapping("/getSwipe")
    public ResponseResult<ArrayList<Swipe>> getSwipe(){

        return indexService.getSwipe();
    }

    @GetMapping("/getHcyw")
    public ResponseResult<ArrayList<FocusNewsOfHC>> getHcyw(){
        return indexService.getHcyw();
    }

    @GetMapping("/getCountrySite")
    public ResponseResult<ArrayList<CountrySite>> getCountrySite(){
        return indexService.getCountrySite();
    }
}
