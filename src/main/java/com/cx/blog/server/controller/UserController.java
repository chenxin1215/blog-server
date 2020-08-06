package com.cx.blog.server.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.blog.server.dto.request.user.AddUserRequest;
import com.cx.blog.server.dto.request.user.UpdateUserInfoRequest;
import com.cx.blog.server.dto.response.PageListViewData;
import com.cx.blog.server.dto.response.SimpleView;
import com.cx.blog.server.dto.response.StringView;
import com.cx.user.client.commom.PasswordEncryService;
import com.cx.user.dto.request.QueryUserBaseInfoCondition;
import com.cx.user.dto.request.SaveUserRequest;
import com.cx.user.dto.response.UserShowInfo;
import com.cx.user.service.IAPISysMenuService;
import com.cx.user.service.IAPIUserBaseInfoService;
import com.cx.user.service.IAPIUserPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author chenxin
 * @date 2020/6/14
 */
@RestController
@RequestMapping("user")
@Api(tags = "用户控制器")
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

    @PostMapping(value = "/addUser")
    @ApiOperation("新增用户")
    public StringView addUser(@RequestBody AddUserRequest request) {
        StringView view = new StringView();

        SaveUserRequest saveUserRequest = new SaveUserRequest();
        BeanUtils.copyProperties(request, saveUserRequest);

        userBaseInfoService.addUser(saveUserRequest);

        view.success("新增成功");
        return view;
    }

    @PostMapping(value = "/updateUser")
    @ApiOperation("修改用户")
    public StringView updateUser(@RequestBody UpdateUserInfoRequest request) {
        StringView view = new StringView();

        SaveUserRequest saveUserRequest = new SaveUserRequest();
        BeanUtils.copyProperties(request, saveUserRequest);

        userBaseInfoService.updateUserBaseInfo(saveUserRequest);

        view.success("修改成功");
        return view;
    }

    @PostMapping(value = "/queryUserList")
    @ApiOperation("用户列表")
    public SimpleView queryUserList(@RequestBody QueryUserBaseInfoCondition condition) {
        SimpleView view = new SimpleView();
        PageListViewData<UserShowInfo> pageListViewData = new PageListViewData<>();

        Page<UserShowInfo> infoList = userBaseInfoService.pageQueryUserBaseInfoList(condition);
        pageListViewData.setTotal((int)infoList.getTotal());
        pageListViewData.setData(infoList.getRecords());

        view.success(pageListViewData);
        return view;
    }

}