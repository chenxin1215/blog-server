package com.cx.blog.server.dto.request.user;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class UpdatePasswordRequest {

    @NotBlank
    @ApiModelProperty("老密码")
    private String oldPassword;

    @NotBlank
    @ApiModelProperty("第一次输入新密码")
    private String newOnePassword;

    @NotBlank
    @ApiModelProperty("第二次输入新密码")
    private String newTwoPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewOnePassword() {
        return newOnePassword;
    }

    public void setNewOnePassword(String newOnePassword) {
        this.newOnePassword = newOnePassword;
    }

    public String getNewTwoPassword() {
        return newTwoPassword;
    }

    public void setNewTwoPassword(String newTwoPassword) {
        this.newTwoPassword = newTwoPassword;
    }

}
