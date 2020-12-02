package com.cx.blog.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cx.blog.entity.pageinfo.WebPageInfo;
import com.cx.blog.server.dto.response.common.SimpleView;
import com.cx.blog.server.dto.response.pageinfo.WebPageInfoView;
import com.cx.blog.service.IAPIWebPageInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 〈页面信息控制器〉
 *
 * @author chenxin
 * @date 2020/10/28
 */
@RestController
@RequestMapping("/pageInfo")
@Api(tags = "页面信息控制器")
public class WebPageInfoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebPageInfoController.class);

    @Reference
    private IAPIWebPageInfoService webPageInfoService;

    @PostMapping(value = "/getPageInfo")
    @ApiOperation("获取页面信息")
    public SimpleView<WebPageInfoView> getPageInfo() {
        LOGGER.info("### getPageInfo start");
        SimpleView<WebPageInfoView> view = new SimpleView<>();
        WebPageInfoView data = new WebPageInfoView();

        WebPageInfo enablePageInfo = webPageInfoService.getEnablePageInfo();
        if (enablePageInfo != null) {
            BeanUtils.copyProperties(enablePageInfo, data);
        }

        view.success(data);

        LOGGER.info("### getPageInfo end");
        return view;
    }

}