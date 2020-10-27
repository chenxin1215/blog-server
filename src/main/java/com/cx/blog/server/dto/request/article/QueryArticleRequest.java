package com.cx.blog.server.dto.request.article;

import com.cx.blog.commom.PageParam;

import io.swagger.annotations.ApiModelProperty;

/**
 * 〈〉
 *
 * @author chenxin
 * @date 2020/10/27
 */
public class QueryArticleRequest extends PageParam {

    @ApiModelProperty("关键字")
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}