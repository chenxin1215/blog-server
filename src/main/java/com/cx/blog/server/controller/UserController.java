package com.cx.blog.server.controller;

import javax.validation.Valid;

import com.cx.user.service.IAPISysMenuService;
import com.cx.user.service.IAPIUserPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cx.blog.server.dto.request.user.AddUserRequest;
import com.cx.blog.server.dto.response.StringView;
import com.cx.user.client.commom.PasswordEncryService;
import com.cx.user.entity.UserBaseInfo;
import com.cx.user.service.IAPIUserBaseInfoService;

import io.swagger.annotations.ApiOperation;

/**
 *
 * @author chenxin
 * @date 2020/6/14
 */
@RestController
@RequestMapping("user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private PasswordEncryService passwordEncryService;

    @Reference
    private IAPIUserBaseInfoService userBaseInfoService;

    @Reference
    private IAPISysMenuService menuService;

    @Reference
    private IAPIUserPermissionService permissionService;

    @GetMapping("hello")
    public String hello() {
        LOGGER.info("hello start");
        return "hello,boy";
    }

    @PostMapping(value = "addUser")
    @ApiOperation("新增用户")
    public StringView addUser(@RequestBody AddUserRequest request) {
        StringView view = new StringView();

        UserBaseInfo user = new UserBaseInfo();
        user.setLoginName(request.getLoginName());
        user.setUserMobile(request.getMobile());
        user.setLoginPassword(passwordEncryService.encode(request.getPassword()));
        user.setUserStatus(request.getStatus());

        userBaseInfoService.addUser(user);

        view.success("新增成功");
        return view;
    }

}