package com.cx.blog.server.dto.response.comment;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈〉
 *
 * @author chenxin
 * @date 2020/10/28
 */
public class CommentReplyView implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("评论回复记录id")
    private Long id;

    @ApiModelProperty("主评论id")
    private Long commentRootId;

    @ApiModelProperty("回复人名称")
    private String replyUserName;

    @ApiModelProperty("回复人邮箱")
    private String replyUserEmail;

    @ApiModelProperty("被回复的评论回复id")
    private Long toReplyId;

    @ApiModelProperty("被回复人邮箱名称")
    private String toReplyUserName;

    @ApiModelProperty("被回复人邮箱")
    private String toReplyUserEmail;

    @ApiModelProperty("回复内容")
    private String replyContent;

    @ApiModelProperty("回复时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date replyTime;

    @ApiModelProperty("评论回复状态")
    private Integer replyState;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommentRootId() {
        return commentRootId;
    }

    public void setCommentRootId(Long commentRootId) {
        this.commentRootId = commentRootId;
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

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public Integer getReplyState() {
        return replyState;
    }

    public void setReplyState(Integer replyState) {
        this.replyState = replyState;
    }
}