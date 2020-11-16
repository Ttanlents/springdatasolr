package com.yjf.springdatasolr.services.impl;

import com.yjf.springdatasolr.dao.GoodsDao;
import com.yjf.springdatasolr.entity.Goods;
import com.yjf.springdatasolr.services.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 余俊锋
 * @date 2020/11/16 17:42
 * @Description
 */

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsDao goodsDao;
    @Override
    public List<HighlightEntry<Goods>> selectHightPage(Integer pageNum,Integer pageSize,String keywords) {

        HighlightPage<Goods> page = goodsDao.findByKeywords(keywords, PageRequest.of(pageNum, pageSize));
        List<HighlightEntry<Goods>> list = page.getHighlighted(); //包含高亮部分的goodsList
        for (HighlightEntry<Goods> entry : list) {
            //将entry的属性，凡是高亮的都替换
            Goods goods = entry.getEntity();
            for (HighlightEntry.Highlight highlight : entry.getHighlights()) {
                if (highlight.getField().getName().equals("name")){
                    goods.setName(highlight.getSnipplets().get(0));
                }else if (highlight.getField().getName().equals("title")){
                    goods.setTitle(highlight.getSnipplets().get(0));
                }
            }
        }

        return list;
    }
}
