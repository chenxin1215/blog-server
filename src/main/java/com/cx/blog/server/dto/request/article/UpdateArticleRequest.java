package com.cx.blog.server.dto.request.article;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 〈编辑文章入参〉
 *
 * @author chenxin
 * @date 2020/10/27
 */
@ApiModel("编辑文章入参")
public class UpdateArticleRequest implements Serializable {

    @ApiModelProperty("文章id")
    private Long articleId;

    @ApiModelProperty("文章标题")
    private String articleTitle;

    @ApiModelProperty("标题图片key")
    private String articleTitleImage;

    @ApiModelProperty("文章类型")
    private Long articleType;

    @ApiModelProperty("文章状态(1:正常；0：屏蔽)")
    private Boolean articleStatus;

    @ApiModelProperty("文章内容")
    private String articleContent;

    @ApiModelProperty("关联标签ids")
    private List<Long> labelIdList;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleTitleImage() {
        return articleTitleImage;
    }

    public void setArticleTitleImage(String articleTitleImage) {
        this.articleTitleImage = articleTitleImage;
    }

    public Long getArticleType() {
        return articleType;
    }

    public void setArticleType(Long articleType) {
        this.articleType = articleType;
    }

    public Boolean getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(Boolean articleStatus) {
        this.articleStatus = articleStatus;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public List<Long> getLabelIdList() {
        return labelIdList;
    }

    public void setLabelIdList(List<Long> labelIdList) {
        this.labelIdList = labelIdList;
    }
}