package com.imooc.mapper;


import com.imooc.pojo.vo.ItemComentInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface ItemsMapperCustom {

    /**
     * 查询评论数量
     * @param itemId
     * @return
     */
    List<Integer> selectcommentCountByLevel(@Param("itemId") String itemId);

    /**
     * 查询商品的评论
     * @param map
     * @return
     */
    List<ItemComentInfoVO> selectItemComentInfo(@Param("paramMap")HashMap map);
}