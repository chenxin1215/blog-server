package com.cx.blog.server.dto.response;

import io.swagger.annotations.ApiModel;

@ApiModel
public class SimpleView extends BaseView<Object> {

    @Override
    public void success(Object object) {
        this.setRspCode(SUCCESS);
        this.setRspData(object);
    }
}
