package com.imooc.service;

import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import com.imooc.pojo.vo.ItemComentInfoVO;
import com.imooc.pojo.vo.ItemCommentVo;
import com.imooc.utils.PagedGridResult;

import java.util.List;

/**
 * @Classname ItemInfoService
 * @Description TODO
 * @Date 2019/11/10 0:44
 * @Created by 于明灏
 */
public interface ItemInfoService {

    /**
     * 查询商品信息
     * @param itemId
     * @return
     */
    Items selectItemInfo(String itemId);

    /**
     * 查询商品图片
     * @param itemId
     * @return
     */
    List<ItemsImg> selectItemImg(String itemId);

    /**
     * 查询商品规格
     * @param itemId
     * @return
     */
    List<ItemsSpec> selectItemsSpec(String itemId);

    /**
     * 查询商品参数
     * @param itemId
     * @return
     */
    ItemsParam selectItemsParam(String itemId);

    /**
     * 根据等级查询商品评价
     * @param itemId
     * @return
     */
    ItemCommentVo selectcommentCountByLevel(String itemId);

    /**
     * 查询评价列表
     * @param itemId
     * @param level
     * @return
     */
    PagedGridResult<ItemComentInfoVO> selectItemComentInfo(String itemId,
                                                           Integer level,
                                                           Integer page,
                                                           Integer pageSize);

}
