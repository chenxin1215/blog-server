package com.cx.blog.server.dto.response;

import io.swagger.annotations.ApiModel;

@ApiModel
public class SimpleView<T> extends BaseView<T> {

    @Override
    public void success(T data) {
        this.setRspCode(SUCCESS);
        this.setRspData(data);
    }

}
