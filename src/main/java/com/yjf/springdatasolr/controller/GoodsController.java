package com.yjf.springdatasolr.controller;

import com.yjf.springdatasolr.entity.Goods;
import com.yjf.springdatasolr.services.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 余俊锋
 * @date 2020/11/16 17:26
 * @Description
 */
@Controller
@RequestMapping("goods")
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    @RequestMapping("toList.html")
    public String toList(){
        return "/list.html";
    }

    @RequestMapping("select/{pageNum}/{pageSize}")
    @ResponseBody
    public List<HighlightEntry<Goods>> toList(@PathVariable  Integer pageNum, @PathVariable Integer pageSize, String keywords){
        System.out.println(keywords);
      return goodsService.selectHightPage(pageNum,pageSize,keywords);
    }
}
