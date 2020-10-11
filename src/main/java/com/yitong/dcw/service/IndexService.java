package com.yitong.dcw.service;

import com.yitong.dcw.VO.Response;
import com.yitong.dcw.VO.ResponseResult;
import com.yitong.dcw.entity.CountrySite;
import com.yitong.dcw.entity.FocusNewsOfHC;
import com.yitong.dcw.entity.Swipe;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @Classname IndexService
 * @Description TODO
 * @Date 2020/10/1 22:59
 * @Created by 邓磊
 */

public interface IndexService {
    ResponseResult<ArrayList<Swipe>> getSwipe();

    ResponseResult<ArrayList<FocusNewsOfHC>> getHcyw();

    ResponseResult<ArrayList<CountrySite>> getCountrySite();
}
