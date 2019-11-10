package com.imooc.service.impl;

import com.imooc.enums.LevelEnum;
import com.imooc.mapper.CategoryMapper;
import com.imooc.mapper.CategoryMapperCustom;
import com.imooc.pojo.Category;
import com.imooc.pojo.vo.CategoryInfoVO;
import com.imooc.pojo.vo.CategoryVO;
import com.imooc.service.CategoryService;
import lombok.experimental.var;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname CategoryServiceImpl
 * @Description TODO
 * @Date 2019/11/9 20:28
 * @Created by 于明灏
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private CategoryMapperCustom categoryMapperCustom;

    @Override
    public List<Category> selectByCategoryFirstLevel() {
        Example categoryExample = new Example(Category.class);
        Example.Criteria criteria = categoryExample.createCriteria();

        criteria.andEqualTo("type", LevelEnum.LEVEL1.type);
        // 获取一级分类
        List<Category> returnList = categoryMapper.selectByExample(categoryExample);

        return returnList;
    }

    @Override
    public List<CategoryVO> selectCategorySecondLevel(Integer rootCatId) {
        List<CategoryVO> returnList = categoryMapperCustom.selectCategorySecondLevel(rootCatId);
        return returnList;
    }

    @Override
    public List<CategoryInfoVO> selectSixNewItems(Integer rootCatId) {
        return categoryMapperCustom.selectSixNewItems(rootCatId);
    }
}
