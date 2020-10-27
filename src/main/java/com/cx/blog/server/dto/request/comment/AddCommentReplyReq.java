package com.cx.blog.server.dto.request.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 *
 * @author chenxin
 * @date 2020/10/27
 */
@ApiModel("新增评论回复入参")
public class AddCommentReplyReq implements Serializable{

    @ApiModelProperty("主评论id")
    private Long commentRootId;

    @ApiModelProperty("回复人id")
    private Long replyUserId;

    @ApiModelProperty("回复人名称")
    private String replyUserName;

    @ApiModelProperty("回复人邮箱")
    private String replyUserEmail;

    @ApiModelProperty("被回复的评论回复id")
    private Long toReplyId;

    @ApiModelProperty("被回复人id")
    private Long toReplyUserId;

    @ApiModelProperty("被回复人名称")
    private String toReplyUserName;

    @ApiModelProperty("被回复人邮箱")
    private String toReplyUserEmail;

    @ApiModelProperty("回复内容")
    private String replyContent;

    public Long getCommentRootId() {
        return commentRootId;
    }

    public void setCommentRootId(Long commentRootId) {
        this.commentRootId = commentRootId;
    }

    public Long getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(Long replyUserId) {
        this.replyUserId = replyUserId;
    }

    public String getReplyUserName() {
        return replyUserName;
    }

    public void setReplyUserName(String replyUserName) {
        this.replyUserName = replyUserName;
    }

    public String getReplyUserEmail() {
        return replyUserEmail;
    }

    public void setReplyUserEmail(String replyUserEmail) {
        this.replyUserEmail = replyUserEmail;
    }

    public Long getToReplyId() {
        return toReplyId;
    }

    public void setToReplyId(Long toReplyId) {
        this.toReplyId = toReplyId;
    }

    public Long getToReplyUserId() {
        return toReplyUserId;
    }

    public void setToReplyUserId(Long toReplyUserId) {
        this.toReplyUserId = toReplyUserId;
    }

    public String getToReplyUserName() {
        return toReplyUserName;
    }

    public void setToReplyUserName(String toReplyUserName) {
        this.toReplyUserName = toReplyUserName;
    }

    public String getToReplyUserEmail() {
        return toReplyUserEmail;
    }

    public void setToReplyUserEmail(String toReplyUserEmail) {
        this.toReplyUserEmail = toReplyUserEmail;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }
}