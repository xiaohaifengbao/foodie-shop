package com.imooc.service;

import com.imooc.pojo.Category;
import com.imooc.pojo.vo.CategoryVO;

import java.util.List;

/**
 * @Classname CategoryService
 * @Description TODO
 * @Date 2019/11/9 20:28
 * @Created by 于明灏
 */
public interface CategoryService {

    /**
     * 查询一级分类
     * @return
     */
    List<Category> selectByCategoryFirstLevel();

    /**
     * 查询二级分类和三级分类
     * @param rootCatId
     * @return
     */
    List<CategoryVO> selectCategorySecondLevel(Integer rootCatId);
}
