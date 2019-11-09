package com.imooc.service;

import com.imooc.pojo.Carousel;

import java.util.List;

/**
 * @Classname CarouselService
 * @Description TODO
 * @Date 2019/11/9 20:15
 * @Created by 于明灏
 */
public interface CarouselService {

    /**
     * 展示首页轮播图
     * @return
     */
    List<Carousel> selectCarousel();
}
