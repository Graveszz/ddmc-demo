package com.zhuhang.ddmc.vo.product;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AttrGroupQueryVo {
	
	@ApiModelProperty(value = "组名")
	private String name;

}
