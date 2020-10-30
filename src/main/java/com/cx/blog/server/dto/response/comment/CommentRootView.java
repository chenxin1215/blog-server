package com.cx.blog.server.dto.response.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈主评论信息〉
 *
 * @author chenxin
 * @date 2020/10/28
 */
@ApiModel("主评论信息")
public class CommentRootView implements Serializable{

    @ApiModelProperty("评论id")
    private Long commentId;

    @ApiModelProperty("被评论对象id")
    private Long ownerId;

    @ApiModelProperty("被评论对象类型 1-本系统 2-文章")
    private Integer ownerType;

    @ApiModelProperty("评论者名称")
    private String fromUserName;

    @ApiModelProperty("评论者邮箱")
    private String fromUserEmail;

    @ApiModelProperty("评论内容")
    private String commentContent;

    @ApiModelProperty("评论状态")
    private Integer state;

    @ApiModelProperty("评论时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date commentTime;

    @ApiModelProperty("是否置顶")
    private Boolean isTop;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public Boolean getTop() {
        return isTop;
    }

    public void setTop(Boolean top) {
        isTop = top;
    }
}