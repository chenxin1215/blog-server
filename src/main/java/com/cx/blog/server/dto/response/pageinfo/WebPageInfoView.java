package com.cx.blog.server.dto.response.pageinfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 〈〉
 *
 * @author chenxin
 * @date 2020/10/28
 */
@ApiModel("页面配置信息")
public class WebPageInfoView implements Serializable {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("座右铭")
    private String motto;

    @ApiModelProperty("首页中间内容")
    private String indexInfo;

    @ApiModelProperty("底部座右铭")
    private String bottomMotto;

    @ApiModelProperty("个人信息")
    private String personInfo;

    @ApiModelProperty("个人技能")
    private String personSkills;

    @ApiModelProperty("QQ")
    private String qq;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("地址")
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getIndexInfo() {
        return indexInfo;
    }

    public void setIndexInfo(String indexInfo) {
        this.indexInfo = indexInfo;
    }

    public String getBottomMotto() {
        return bottomMotto;
    }

    public void setBottomMotto(String bottomMotto) {
        this.bottomMotto = bottomMotto;
    }

    public String getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(String personInfo) {
        this.personInfo = personInfo;
    }

    public String getPersonSkills() {
        return personSkills;
    }

    public void setPersonSkills(String personSkills) {
        this.personSkills = personSkills;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}