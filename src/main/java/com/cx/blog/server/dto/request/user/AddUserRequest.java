package com.cx.blog.server.dto.request.user;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class AddUserRequest {

    @NotBlank
    @ApiModelProperty("登录名")
    private String loginName;

    @NotBlank
    @ApiModelProperty("员工号")
    private String staffNo;

    @NotBlank
    @ApiModelProperty("手机号")
    private String mobile;

    @NotBlank
    @ApiModelProperty("密码")
    private String password;

    @NotNull
    @ApiModelProperty("状态, 0:启用，1：禁用")
    private Integer status;

    @NotNull
    @ApiModelProperty("角色列表")
    private List<Long> roleIdList;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

}
