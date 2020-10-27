package com.cx.blog.server.dto.request.common;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 *
 * @author chenxin
 * @date 2020/10/27
 */
public class IdRequest implements Serializable{
    
    @ApiModelProperty("id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}