package com.imooc.mapper;

import com.imooc.pojo.vo.center.CenterOrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Classname OrderMapperCustom
 * @Description TODO
 * @Date 2020/1/30 23:47
 * @Created by 于明灏
 */
@Mapper
public interface OrderMapperCustom {

    /**
     * 查询订单信息
     * @param map
     * @return
     */
    List<CenterOrderVO> queryCenterOrderInfo(@Param("paramMap") Map<String, Object> map);
}
