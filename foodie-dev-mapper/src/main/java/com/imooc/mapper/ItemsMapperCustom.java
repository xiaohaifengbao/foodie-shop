package com.imooc.mapper;


import com.imooc.pojo.vo.ItemComentInfoVO;
import com.imooc.pojo.vo.ItemSearchVO;
import com.imooc.pojo.vo.ShopCartVO;
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

    /**
     * 根据关键字搜索商品
     * @param map
     * @return
     */
    List<ItemSearchVO> searchItemByKeywords(@Param("paramsMap") HashMap map);

    /**
     * 根据分类搜索商品
     * @param map
     * @return
     */
    List<ItemSearchVO> searchItemByCatId(@Param("paramsMap") HashMap map);

    /**
     * 刷新购物车
     * @param paramsList
     * @return
     */
    List<ShopCartVO> refreshShopCart(@Param("paramsList") List<String> paramsList);
}