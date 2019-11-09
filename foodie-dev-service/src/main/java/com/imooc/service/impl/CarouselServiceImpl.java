package com.imooc.service.impl;

import com.imooc.enums.YesOrNoEnum;
import com.imooc.mapper.CarouselMapper;
import com.imooc.pojo.Carousel;
import com.imooc.service.CarouselService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname CarouselServiceImpl
 * @Description TODO
 * @Date 2019/11/9 20:16
 * @Created by 于明灏
 */
@Service
public class CarouselServiceImpl implements CarouselService {

    @Resource
    private CarouselMapper carouselMapper;

    @Override
    public List<Carousel> selectCarousel() {
        Example example = new Example(Carousel.class);
        example.orderBy("sort");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isShow", YesOrNoEnum.YES.type);

        List<Carousel> returnList = carouselMapper.selectByExample(example);

        return returnList;
    }
}
