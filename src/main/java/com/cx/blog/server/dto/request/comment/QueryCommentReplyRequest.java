package com.cx.blog.server.dto.request.comment;

import com.cx.blog.commom.PageParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author chenxin
 * @date 2020/10/27
 */
@ApiModel("查询主评论列表条件")
public class QueryCommentReplyRequest extends PageParam {

    @ApiModelProperty("主评论id")
    private Long commentRootId;

    @ApiModelProperty("回复人id")
    private Long replyUserId;

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
}