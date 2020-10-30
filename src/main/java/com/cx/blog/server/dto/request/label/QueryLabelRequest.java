package com.cx.blog.server.dto.request.label;

import com.cx.blog.commom.PageParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 〈查询标签条件〉
 *
 * @author chenxin
 * @date 2020/10/26
 */
@ApiModel("查询标签条件")
public class QueryLabelRequest extends PageParam {

    @ApiModelProperty("关键字")
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}