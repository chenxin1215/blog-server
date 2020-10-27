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
public class QueryCommentRootRequest extends PageParam {

    @ApiModelProperty("评论所属对象id")
    private Long ownerId;

    @ApiModelProperty("评论对象类型")
    private Integer ownerType;

    @ApiModelProperty("评论人id")
    private Long fromUserId;

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

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }
}