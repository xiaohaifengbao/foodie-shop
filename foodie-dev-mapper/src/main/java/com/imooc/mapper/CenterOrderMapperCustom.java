package com.imooc.mapper;

import com.imooc.pojo.vo.center.CenterTrendVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CenterOrderMapperCustom{

    Integer statusCounts(@Param("userId") String userId,
                         @Param("orderStatus") Integer orderStatus);

    List<CenterTrendVO> trend(@Param("userId") String userId);
}
