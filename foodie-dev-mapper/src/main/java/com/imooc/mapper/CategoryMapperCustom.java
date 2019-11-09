package com.imooc.mapper;

import com.imooc.my.mapper.MyMapper;
import com.imooc.pojo.Category;
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
}