package com.yjf.springdatasolr.dao;

import com.yjf.springdatasolr.entity.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.repository.Highlight;
import org.springframework.data.solr.repository.SolrCrudRepository;

/**
 * @author 余俊锋
 * @date 2020/11/16 16:10
 * @Description :继承接口，设置实例泛型和主键泛型
 */
public interface GoodsDao extends SolrCrudRepository<Goods,String> {
    //命名法查询分页
    Page<Goods> findByKeywordsOrderByPriceDesc(String keywords,Pageable pageable);

    @Highlight(fields = {"name","title"},prefix = "<font color='red'>",postfix = "</font>")
    HighlightPage<Goods> findByKeywords(String keywords,Pageable pageable);

}
