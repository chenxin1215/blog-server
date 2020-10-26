package com.cx.blog.server.dto.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel("新增用户入参")
public class AddUserRequest implements Serializable {

    @ApiModelProperty("登录名")
    private String loginName;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("手机号")
    private String userMobile;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("状态, 0:启用，1：禁用")
    private Integer status;

    @ApiModelProperty("角色列表")
    private List<Long> roleIdList;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Long> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
