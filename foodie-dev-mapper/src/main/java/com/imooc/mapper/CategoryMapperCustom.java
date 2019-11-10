package com.imooc.mapper;

import com.imooc.pojo.vo.CategoryInfoVO;
import com.imooc.pojo.vo.CategoryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapperCustom{

    /**
     * 查询二级分类和三级分类
     * @param rootCatId
     * @return
     */
    List<CategoryVO> selectCategorySecondLevel(@Param("rootCatId") Integer rootCatId);

    /**
     * 查询最近的6个商品
     * @param rootCatId
     * @return
     */
    List<CategoryInfoVO> selectSixNewItems(@Param("rootCatId") Integer rootCatId);
}