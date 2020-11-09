package com.cx.blog.server.dto.request.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 〈新增评论入参〉
 *
 * @author chenxin
 * @date 2020/10/27
 */
@ApiModel("新增评论入参")
public class AddCommentRootReq implements Serializable {

    @ApiModelProperty("被评论对象id")
    private Long ownerId;

    @ApiModelProperty("被评论对象类型 1-本系统 2-文章")
    private Integer ownerType;

    @ApiModelProperty("评论者id")
    private Long formUserId;

    @ApiModelProperty("评论者头像")
    private String fromUserHeadImg;

    @ApiModelProperty("评论者名称")
    private String fromUserName;

    @ApiModelProperty("评论者邮箱")
    private String fromUserEmail;

    @ApiModelProperty("评论内容")
    private String commentContent;

    public Long getFormUserId() {
        return formUserId;
    }

    public void setFormUserId(Long formUserId) {
        this.formUserId = formUserId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(Integer ownerType) {
        this.ownerType = ownerType;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getFromUserEmail() {
        return fromUserEmail;
    }

    public void setFromUserEmail(String fromUserEmail) {
        this.fromUserEmail = fromUserEmail;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getFromUserHeadImg() {
        return fromUserHeadImg;
    }

    public void setFromUserHeadImg(String fromUserHeadImg) {
        this.fromUserHeadImg = fromUserHeadImg;
    }
}