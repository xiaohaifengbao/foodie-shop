package com.imooc.mapper;

import com.imooc.pojo.vo.center.MyCommentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Classname MyCommentMapperCustom
 * @Description TODO
 * @Date 2020/2/9 18:27
 * @Created by 于明灏
 */
@Mapper
public interface MyCommentMapperCustom {


    List<MyCommentVO> query(@Param("userId") String userId);
}
