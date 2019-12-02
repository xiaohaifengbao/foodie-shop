package com.imooc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.enums.YesOrNoEnum;
import com.imooc.mapper.*;
import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import com.imooc.pojo.vo.ItemComentInfoVO;
import com.imooc.pojo.vo.ItemCommentVo;
import com.imooc.pojo.vo.ItemSearchVO;
import com.imooc.pojo.vo.ShopCartVO;
import com.imooc.service.ItemInfoService;
import com.imooc.utils.DesensitizationUtil;
import com.imooc.utils.PagedGridResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @Classname ItemInfoServiceImpl
 * @Description TODO
 * @Date 2019/11/10 0:50
 * @Created by 于明灏
 */
@Service
public class ItemInfoServiceImpl implements ItemInfoService {

    @Resource
    private ItemsMapper itemsMapper;
    @Resource
    private ItemsParamMapper itemsParamMapper;
    @Resource
    private ItemsSpecMapper itemsSpecMapper;
    @Resource
    private ItemsImgMapper itemsImgMapper;
    @Resource
    private ItemsMapperCustom itemsMapperCustom;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsImg> selectItemImg(String itemId) {
        Example example = new Example(ItemsImg.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemId);

        return itemsImgMapper.selectByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Items selectItemInfo(String itemId) {
        Example example = new Example(Items.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", itemId);

        return itemsMapper.selectOneByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsSpec> selectItemsSpec(String itemId) {
        Example example = new Example(ItemsSpec.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemId);

        return itemsSpecMapper.selectByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsParam selectItemsParam(String itemId) {
        Example example = new Example(ItemsParam.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemId);

        return itemsParamMapper.selectOneByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemCommentVo selectcommentCountByLevel(String itemId) {
        ItemCommentVo returnModel = new ItemCommentVo();
        // 查询所有评价
        List<Integer> commentList = itemsMapperCustom.selectcommentCountByLevel(itemId);
        returnModel.setGoodCounts(commentList.get(2));
        returnModel.setNormalCounts(commentList.get(1));
        returnModel.setBadCounts(commentList.get(0));
        // 全部评论求和
        returnModel.setTotalCounts(commentList.get(0) + commentList.get(1) + commentList.get(2));

        return returnModel;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult<ItemComentInfoVO> selectItemComentInfo(String itemId,
                                                                  Integer level,
                                                                  Integer page,
                                                                  Integer pageSize) {
        HashMap<Object, Object> map = new HashMap();
        map.put("itemId",itemId);
        map.put("level",level);
        // 分页
        PageHelper.startPage(page, pageSize);
        // 获取商品集合
        List<ItemComentInfoVO> itemList = itemsMapperCustom.selectItemComentInfo(map);
        // 信息脱敏
        itemList.forEach(itemModel-> {
            itemModel.setNickname(DesensitizationUtil.commonDisplay(itemModel.getNickname()));
        });
        PageInfo pageInfo = new PageInfo(itemList);

        return new PagedGridResult<ItemComentInfoVO>(pageInfo);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult<ItemSearchVO> searchItemByKeywords(String keywords, String sort, Integer page, Integer pageSize) {

        HashMap paramsMap = new HashMap();
        paramsMap.put("keywords", keywords);
        paramsMap.put("sort", sort);

        PageHelper.startPage(page, pageSize);

        List<ItemSearchVO> itemList = itemsMapperCustom.searchItemByKeywords(paramsMap);

        PageInfo pageInfo = new PageInfo(itemList);

        PagedGridResult<ItemSearchVO> returnModel = new PagedGridResult<ItemSearchVO>(pageInfo);
        return returnModel;

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult<ItemSearchVO> searchItemByKeywords(Integer catId, String sort, Integer page, Integer pageSize) {
        HashMap paramsMap = new HashMap();
        paramsMap.put("catId", catId);
        paramsMap.put("sort", sort);

        PageHelper.startPage(page, pageSize);

        List<ItemSearchVO> itemList = itemsMapperCustom.searchItemByCatId(paramsMap);

        PageInfo pageInfo = new PageInfo(itemList);

        PagedGridResult<ItemSearchVO> returnModel = new PagedGridResult<ItemSearchVO>(pageInfo);
        return returnModel;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ShopCartVO> refreshShopCart(String specIds) {

        String[] specArray = specIds.split(",");
        return itemsMapperCustom.refreshShopCart(Arrays.asList(specArray));
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsSpec queryItemInfoBySpecId(String specId) {

        ItemsSpec itemsSpec = new ItemsSpec();
        itemsSpec.setId(specId);

        return itemsSpecMapper.selectOne(itemsSpec);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsImg queryItemImgById(String itemId) {

        ItemsImg itemsImg = new ItemsImg();
        itemsImg.setItemId(itemId);
        itemsImg.setIsMain(YesOrNoEnum.YES.type);

        return itemsImgMapper.selectOne(itemsImg);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void decreaseStock(String specId, Integer stock) {
        // 乐观锁
            // 减少库存
        int result = itemsMapperCustom.decreaseStack(specId, stock);

        if(result != 1) {
            throw new RuntimeException("订单创建失败, 原因：库存不足！");
        }
    }


}
