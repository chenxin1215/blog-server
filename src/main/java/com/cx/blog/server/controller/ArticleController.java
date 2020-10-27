package com.cx.blog.server.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cx.blog.entity.article.Article;
import com.cx.blog.server.dto.response.PageListViewData;
import com.cx.utils.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cx.blog.dto.request.article.QueryArticleCondition;
import com.cx.blog.dto.request.article.SaveArticleRequest;
import com.cx.blog.dto.response.ArticleDetail;
import com.cx.blog.server.dto.request.article.AddArticleRequest;
import com.cx.blog.server.dto.request.article.QueryArticleRequest;
import com.cx.blog.server.dto.request.article.UpdateArticleRequest;
import com.cx.blog.server.dto.request.common.IdRequest;
import com.cx.blog.server.dto.response.SimpleView;
import com.cx.blog.server.dto.response.StringView;
import com.cx.blog.service.IAPIArticleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 〈文章控制器〉
 *
 * @author chenxin
 * @date 2020/10/27
 */
@RestController
@RequestMapping("/article")
@Api(tags = "文章控制器")
public class ArticleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

    @Reference
    private IAPIArticleService articleService;

    @PostMapping(value = "/addArticle")
    @ApiOperation("新增文章")
    public StringView addArticle(@RequestBody AddArticleRequest request) {
        LOGGER.info("### addArticle start req:{}", JsonUtil.toString(request));
        StringView view = new StringView();

        SaveArticleRequest saveArticleRequest = new SaveArticleRequest();
        BeanUtils.copyProperties(request, saveArticleRequest);
        articleService.addArticle(saveArticleRequest);

        view.success("新增成功");

        LOGGER.info("### addArticle end");
        return view;
    }

    @PostMapping(value = "/updateArticle")
    @ApiOperation("编辑文章")
    public StringView updateArticle(@RequestBody UpdateArticleRequest request) {
        LOGGER.info("### updateArticle start req:{}", JsonUtil.toString(request));
        StringView view = new StringView();

        SaveArticleRequest saveArticleRequest = new SaveArticleRequest();
        BeanUtils.copyProperties(request, saveArticleRequest);
        articleService.updateArticle(saveArticleRequest);

        view.success("编辑成功");

        LOGGER.info("### updateArticle end");
        return view;
    }

    @PostMapping(value = "/queryArticleDetail")
    @ApiOperation("文章详情")
    public SimpleView queryArticleDetail(@RequestBody IdRequest request) {
        LOGGER.info("### queryArticleDetail start req:{}", JsonUtil.toString(request));
        SimpleView view = new SimpleView();

        ArticleDetail articleDetail = articleService.queryArticleDetail(request.getId());
        view.success(articleDetail);

        LOGGER.info("### queryArticleDetail end");
        return view;
    }

    @PostMapping(value = "/queryArticleInfoList")
    @ApiOperation("文章列表查询")
    public PageListViewData<Article> queryArticleInfoList(@RequestBody QueryArticleRequest request) {
        LOGGER.info("### queryArticleInfoList start req:{}", JsonUtil.toString(request));
        PageListViewData<Article> view = new PageListViewData<>();

        QueryArticleCondition condition = new QueryArticleCondition();
        BeanUtils.copyProperties(request, condition);
        IPage<Article> articleIPage = articleService.pageQueryArticleInfoList(condition);

        view.setData(articleIPage.getRecords());
        view.setTotal((int)articleIPage.getTotal());

        LOGGER.info("### queryArticleInfoList end");
        return view;
    }

}