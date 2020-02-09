package com.imooc.pojo.vo.center;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Classname CenterTrendVO
 * @Description TODO
 * @Date 2020/2/9 20:21
 * @Created by 于明灏
 */
@Data
public class CenterTrendVO {

    private String orderId;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date payTime;

    private Integer orderStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createdTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date deliverTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date successTime;

}
