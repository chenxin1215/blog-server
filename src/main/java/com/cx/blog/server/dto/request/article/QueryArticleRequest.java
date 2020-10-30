package com.cx.blog.server.dto.request.article;

import com.cx.blog.commom.PageParam;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 〈〉
 *
 * @author chenxin
 * @date 2020/10/27
 */
public class QueryArticleRequest extends PageParam {

    @ApiModelProperty("关键字")
    private String keyword;

    @ApiModelProperty("排序类型 1-推荐(置顶) 2-发布时间 3-热评")
    private Integer sortType;

    @ApiModelProperty("标签类型")
    private List<Long> labelIdList;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getSortType() {
        return sortType;
    }

    public void setSortType(Integer sortType) {
        this.sortType = sortType;
    }

    public List<Long> getLabelIdList() {
        return labelIdList;
    }

    public void setLabelIdList(List<Long> labelIdList) {
        this.labelIdList = labelIdList;
    }
}