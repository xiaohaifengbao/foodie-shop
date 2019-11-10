package com.imooc.controller;

import com.imooc.pojo.*;
import com.imooc.pojo.vo.ItemInfoVO;
import com.imooc.service.ItemInfoService;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class ItemsController {

    @Autowired
    private ItemInfoService itemInfoService;

    @ApiOperation(value = "商品详细信息", notes = "商品详细信息", httpMethod = "GET")
    @GetMapping(value = "/info/{itemId}", produces = "application/json;charset=utf-8")
    public IMOOCJSONResult selectItemInfo(@PathVariable
                                              @ApiParam(name = "itemId", value = "商品ID", example = "1", required = true) String itemId) {

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
}
