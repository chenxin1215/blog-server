package com.cx.blog.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cx.blog.dto.request.label.QueryLabelCondition;
import com.cx.blog.dto.request.label.SaveLabelRequest;
import com.cx.blog.entity.label.Label;
import com.cx.blog.server.dto.request.label.QueryLabelRequest;
import com.cx.blog.server.dto.response.common.PageListViewData;
import com.cx.blog.server.dto.response.common.StringView;
import com.cx.blog.service.IAPILabelService;
import com.cx.utils.util.JsonUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 〈标签控制器〉
 *
 * @author chenxin
 * @date 2020/10/30
 */
@RestController
@RequestMapping("/label")
@Api(tags = "标签控制器")
public class LabelController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LabelController.class);

    @Reference
    private IAPILabelService labelService;

    @PostMapping(value = "/queryLabelList")
    @ApiOperation("标签列表查询")
    public PageListViewData<Label> queryLabelList(@RequestBody QueryLabelRequest request) {
        LOGGER.info("### queryLabelList start req:{}", JsonUtil.toString(request));
        PageListViewData<Label> view = new PageListViewData<>();

        QueryLabelCondition condition = new QueryLabelCondition();
        BeanUtils.copyProperties(request, condition);
        IPage<Label> iPageData = labelService.queryLabelList(condition);

        view.setTotal((int)iPageData.getTotal());
        view.setData(iPageData.getRecords());

        LOGGER.info("### queryLabelList end");
        return view;
    }

    @PostMapping(value = "/addLabel")
    @ApiOperation("新增标签")
    public StringView addLabel(@RequestBody SaveLabelRequest request) {
        LOGGER.info("### addLabel start req:{}", JsonUtil.toString(request));
        StringView view = new StringView();

        labelService.addLabel(request);
        view.success("新增成功");

        LOGGER.info("### addLabel end");
        return view;
    }

}