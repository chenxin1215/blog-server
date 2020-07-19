package com.cx.blog.server.dto.response;

public class StringView extends BaseView<String> {

    @Override
    public void success(String rspMsg) {
        this.setRspCode(SUCCESS);
        this.setRspMsg(rspMsg);
        this.setRspData(null);
    }
}
