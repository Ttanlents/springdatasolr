package com.yjf.springdatasolr.services;

import com.yjf.springdatasolr.entity.Goods;
import org.springframework.data.solr.core.query.result.HighlightEntry;

import java.util.List;

/**
 * @author 余俊锋
 * @date 2020/11/16 17:42
 * @Description
 */
public interface GoodsService {

    List<HighlightEntry<Goods>> selectHightPage(Integer pageNum, Integer pageSize, String keywords);

}
