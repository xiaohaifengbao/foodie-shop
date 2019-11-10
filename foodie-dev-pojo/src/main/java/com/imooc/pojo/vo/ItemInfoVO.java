package com.imooc.pojo.vo;

import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Classname ItemInfoVO
 * @Description TODO
 * @Date 2019/11/10 1:00
 * @Created by 于明灏
 */
@ApiModel("商品页面信息")
@Data
public class ItemInfoVO {

    @ApiModelProperty("商品信息")
    private Items item;

    @ApiModelProperty("商品图片信息")
    private List<ItemsImg> itemImgList;

    @ApiModelProperty("商品规格")
    private List<ItemsSpec> itemSpecList;

    @ApiModelProperty("商品参数")
    private ItemsParam itemParams;
}
