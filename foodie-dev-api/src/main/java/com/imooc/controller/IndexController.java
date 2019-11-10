package com.imooc.controller;

import com.imooc.pojo.Carousel;
import com.imooc.pojo.Category;
import com.imooc.pojo.vo.CategoryInfoVO;
import com.imooc.pojo.vo.CategoryVO;
import com.imooc.service.CarouselService;
import com.imooc.service.CategoryService;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Classname IndexController
 * @Description TODO
 * @Date 2019/11/9 20:13
 * @Created by 于明灏
 */

@Api(value = "首页", tags = {"首页分类推荐"})
@RestController
@RequestMapping("/index")
@Slf4j
public class IndexController {

    @Autowired
    private CarouselService carouselService;

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "首页轮播图", notes = "首页轮播图", httpMethod = "GET")
    @GetMapping(value = "/carousel", produces = "application/json;charset=utf-8")
    public IMOOCJSONResult selectByCarousel() {
        try {
            List<Carousel> carousels = carouselService.selectCarousel();
            return IMOOCJSONResult.ok(carousels);
        } catch (Exception e) {
            log.error("loginUser error [{}]", e.getMessage());
            return IMOOCJSONResult.errorException(e.getMessage());
        }
    }

    @ApiOperation(value = "一级分类", notes = "一级分类", httpMethod = "GET")
    @GetMapping(value = "/cats", produces = "application/json;charset=utf-8")
    public IMOOCJSONResult selectByCats() {
        try {
            List<Category> categories = categoryService.selectByCategoryFirstLevel();
            return IMOOCJSONResult.ok(categories);
        } catch (Exception e) {
            log.error("selectByCats error [{}]", e.getMessage());
            return IMOOCJSONResult.errorException(e.getMessage());
        }
    }

    @ApiOperation(value = "二级分类", notes = "二级分类", httpMethod = "GET")
    @GetMapping(value = "/subCat/{rootCatId}", produces = "application/json;charset=utf-8")
    public IMOOCJSONResult selectByCategoryId(@PathVariable
                                        @ApiParam(name = "rootCatId", value = "一级分类ID", example = "1", required = true) Integer rootCatId) {
        try {
            List<CategoryVO> categoryVOS = categoryService.selectCategorySecondLevel(rootCatId);
            return IMOOCJSONResult.ok(categoryVOS);
        } catch (Exception e) {
            log.error("selectByCategoryId error [{}]", e.getMessage());
            return IMOOCJSONResult.errorException(e.getMessage());
        }
    }

    @ApiOperation(value = "下拉查询最新商品", notes = "下拉查询最新商品", httpMethod = "GET")
    @GetMapping(value = "/sixNewItems/{rootCatId}", produces = "application/json;charset=utf-8")
    public IMOOCJSONResult selectSixNewItems(@PathVariable
                                              @ApiParam(name = "rootCatId", value = "一级分类ID", example = "1", required = true) Integer rootCatId) {
        try {
            List<CategoryInfoVO> categoryInfoVOS = categoryService.selectSixNewItems(rootCatId);
            return IMOOCJSONResult.ok(categoryInfoVOS);
        } catch (Exception e) {
            log.error("selectSixNewItems error [{}]", e.getMessage());
            return IMOOCJSONResult.errorException(e.getMessage());
        }
    }
}
