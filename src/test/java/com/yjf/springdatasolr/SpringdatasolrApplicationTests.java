package com.yjf.springdatasolr;

import com.yjf.springdatasolr.dao.GoodsDao;
import com.yjf.springdatasolr.entity.Goods;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.solr.core.query.Field;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;

import java.util.List;

@SpringBootTest
class SpringdatasolrApplicationTests {
    @Autowired
    GoodsDao goodsDao;

    @Test  //查询全部
    public void demo01() {
        goodsDao.findAll().forEach(n->{
            System.out.println(n);
        });
    }

    @Test   //分页查询
    public void demo02() {
        Page<Goods> page = goodsDao.findByKeywordsOrderByPriceDesc("手机", PageRequest.of(0, 5));
        page.getContent().forEach(n->{
            System.out.println(n);
        });
    }

    @Test   //高亮查询
    public void demo03() {
        HighlightPage<Goods> page = goodsDao.findByKeywords("手机", PageRequest.of(0, 5));
        List<Goods> goodsList = page.getContent();
        List<HighlightEntry<Goods>> list = page.getHighlighted();
        for (HighlightEntry<Goods> entry : list) {
            Goods goods = entry.getEntity();

            List<HighlightEntry.Highlight> highlights = entry.getHighlights();
            for (HighlightEntry.Highlight highlight : highlights) {
                Field field = highlight.getField();
                String name = field.getName();
                System.out.println("-->"+name);
                List<String> strings = highlight.getSnipplets();
                for (String s : strings) {
                    System.out.println("-->"+s);
                }
                System.out.println("===============");
            }

        }
    }

}
