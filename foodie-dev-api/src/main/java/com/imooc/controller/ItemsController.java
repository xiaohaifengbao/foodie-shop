package com.imooc.controller;

import com.imooc.pojo.*;
import com.imooc.pojo.vo.*;
import com.imooc.service.ItemInfoService;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.PagedGridResult;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import java.util.List;

/**
 * @Classname ItemsController
 * @Description TODO
 * @Date 2019/11/10 0:55
 * @Created by 于明灏
 */
@Api(value = "商品", tags = {"商品页面详细信息"})
@RestController
@RequestMapping("/items")
public class ItemsController extends BaseController{

    @Autowired
    private ItemInfoService itemInfoService;

    @ApiOperation(value = "商品详细信息", notes = "商品详细信息", httpMethod = "GET")
    @GetMapping(value = "/info/{itemId}", produces = "application/json;charset=utf-8")
    public IMOOCJSONResult selectItemInfo(@PathVariable
                                              @ApiParam(name = "itemId", value = "商品ID", example = "1", required = true) String itemId) {

        if(StringUtils.isBlank(itemId)) {
            return IMOOCJSONResult.errorMsg("商品不存在");
        }

        ItemInfoVO resultModel = new ItemInfoVO();

        Items items = itemInfoService.selectItemInfo(itemId);
        resultModel.setItem(items);

        List<ItemsImg> itemsImgs = itemInfoService.selectItemImg(itemId);
        resultModel.setItemImgList(itemsImgs);

        ItemsParam itemsParam = itemInfoService.selectItemsParam(itemId);
        resultModel.setItemParams(itemsParam);

        List<ItemsSpec> itemsSpecs = itemInfoService.selectItemsSpec(itemId);
        resultModel.setItemSpecList(itemsSpecs);

        return IMOOCJSONResult.ok(resultModel);
    }

    @ApiOperation(value = "商品评价数量", notes = "商品评价数量", httpMethod = "GET")
    @GetMapping(value = "/commentLevel", produces = "application/json;charset=utf-8")
    public IMOOCJSONResult selectcommentCountByLevel(@RequestParam("itemId") String itemId) {

        if(StringUtils.isBlank(itemId)) {
            return IMOOCJSONResult.errorMsg(null);
        }
        // 查询全部评价
        ItemCommentVo itemCommentVo = itemInfoService.selectcommentCountByLevel(itemId);

        return IMOOCJSONResult.ok(itemCommentVo);
    }

    @ApiOperation(value = "分页查看评价信息", notes = "分页查看评价信息", httpMethod = "GET")
    @GetMapping(value = "/comments", produces = "application/json;charset=utf-8")
    public IMOOCJSONResult selectItemComentInfo(@ApiParam(name = "itemId", value = "商品ID",example = "cake-1001", required = true)
                                                    @RequestParam("itemId") String itemId,
                                                @ApiParam(name = "level", value = "好评等级",example = "1", required = false)
                                                    @RequestParam("level") Integer level,
                                                @ApiParam(name = "page", value = "第几页",example = "1", required = false)
                                                    @RequestParam("page") Integer page,
                                                @ApiParam(name = "pageSize", value = "每页记录数",example = "10", required = false)
                                                    @RequestParam("pageSize") Integer pageSize) {
        if(StringUtils.isBlank(itemId)) {
            return IMOOCJSONResult.errorMsg("商品不存在");
        }
        if(page == null) {
            page = ItemsController.HOME;
        }
        if(pageSize == null) {
            pageSize = ItemsController.RECORD;
        }

        PagedGridResult<ItemComentInfoVO> returnModel =
                itemInfoService.selectItemComentInfo(itemId, level, page, pageSize);

        return IMOOCJSONResult.ok(returnModel);
    }

    @ApiOperation(value = "根据关键字搜索商品", notes = "根据关键字搜索商品", httpMethod = "GET")
    @GetMapping(value = "/search", produces = "application/json;charset=utf-8")
    public IMOOCJSONResult searchtItemBykeywords(@ApiParam(name = "keywords", value = "关键字",example = "天天吃货", required = true)
                                                @RequestParam("keywords") String keywords,
                                                @ApiParam(name = "sort", value = "排序",example = "p", required = true)
                                                @RequestParam("sort") String sort,
                                                @ApiParam(name = "page", value = "第几页",example = "1", required = false)
                                                @RequestParam("page") Integer page,
                                                @ApiParam(name = "pageSize", value = "每页记录数",example = "20", required = false)
                                                @RequestParam("pageSize") Integer pageSize) {


        if(page == null) {
            page = ItemsController.HOME;
        }
        if(pageSize == null) {
            pageSize = ItemsController.ITEM_RECORD;
        }


        PagedGridResult<ItemSearchVO> returnModel = itemInfoService
                .searchItemByKeywords(keywords, sort, page, pageSize);

        return IMOOCJSONResult.ok(returnModel);
    }

    @ApiOperation(value = "根据分类搜索商品", notes = "根据分类搜索商品", httpMethod = "GET")
    @GetMapping(value = "/catItems", produces = "application/json;charset=utf-8")
    public IMOOCJSONResult searchtItemBykeywords(@ApiParam(name = "catId", value = "分类ID",example = "37", required = true)
                                                 @RequestParam("catId") Integer catId,
                                                 @ApiParam(name = "sort", value = "排序",example = "p", required = true)
                                                 @RequestParam("sort") String sort,
                                                 @ApiParam(name = "page", value = "第几页",example = "1", required = false)
                                                 @RequestParam("page") Integer page,
                                                 @ApiParam(name = "pageSize", value = "每页记录数",example = "20", required = false)
                                                 @RequestParam("pageSize") Integer pageSize) {


        if(page == null) {
            page = ItemsController.HOME;
        }
        if(pageSize == null) {
            pageSize = ItemsController.ITEM_RECORD;
        }


        PagedGridResult<ItemSearchVO> returnModel = itemInfoService
                .searchItemByKeywords(catId, sort, page, pageSize);

        return IMOOCJSONResult.ok(returnModel);
    }

    @ApiOperation(value = "刷新购物车", notes = "刷新购物车", httpMethod = "GET")
    @GetMapping(value = "/refresh", produces = "application/json;charset=utf-8")
    public IMOOCJSONResult searchtItemBykeywords(@ApiParam(name = "itemSpecIds", value = "规格ID",example = "1,2,3", required = false)
                                                 @RequestParam("itemSpecIds") String itemSpecIds) {

        List<ShopCartVO> shopCartList = itemInfoService.refreshShopCart(itemSpecIds);

        return IMOOCJSONResult.ok(shopCartList);
    }

}
