package com.imooc.pojo.vo.center;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname StatusCountVO
 * @Description TODO
 * @Date 2020/2/9 20:01
 * @Created by 于明灏
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusCountVO {

    private Integer waitPayCounts = 0;

    private Integer waitDeliverCounts = 0;

    private Integer waitReceiveCounts = 0;

    private Integer waitCommentCounts = 0;
}
