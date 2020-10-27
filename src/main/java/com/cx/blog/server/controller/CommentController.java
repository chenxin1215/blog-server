package com.cx.blog.server.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cx.blog.dto.request.comment.AddCommentReplyRequest;
import com.cx.blog.dto.request.comment.AddCommentRootRequest;
import com.cx.blog.dto.request.comment.QueryCommentReplyCondition;
import com.cx.blog.dto.request.comment.QueryCommentRootCondition;
import com.cx.blog.entity.comment.CommentReply;
import com.cx.blog.entity.comment.CommentRoot;
import com.cx.blog.server.dto.request.comment.AddCommentReplyReq;
import com.cx.blog.server.dto.request.comment.AddCommentRootReq;
import com.cx.blog.server.dto.request.comment.QueryCommentReplyRequest;
import com.cx.blog.server.dto.request.comment.QueryCommentRootRequest;
import com.cx.blog.server.dto.request.common.IdRequest;
import com.cx.blog.server.dto.response.PageListViewData;
import com.cx.blog.server.dto.response.StringView;
import com.cx.blog.server.util.SecurityUtils;
import com.cx.blog.service.IAPICommentService;
import com.cx.utils.util.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈评论控制器〉
 *
 * @author chenxin
 * @date 2020/10/27
 */
@RestController
@RequestMapping("/comment")
@Api(tags = "评论控制器")
public class CommentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

    @Reference
    private IAPICommentService commentService;

    @PostMapping(value = "/addCommentRoot")
    @ApiOperation("新增主评论")
    public StringView addCommentRoot(@RequestBody AddCommentRootReq request) {
        LOGGER.info("### addCommentRoot start req:{}", JsonUtil.toString(request));
        StringView view = new StringView();

        AddCommentRootRequest addCommentRootRequest = new AddCommentRootRequest();
        BeanUtils.copyProperties(request, addCommentRootRequest);
        commentService.addCommentRoot(addCommentRootRequest);
        view.success("新增成功");

        LOGGER.info("### addCommentRoot end");
        return view;
    }

    @PostMapping(value = "/addCommentReply")
    @ApiOperation("新增评论回复")
    public StringView addCommentReply(@RequestBody AddCommentReplyReq request) {
        LOGGER.info("### addCommentReply start req:{}", JsonUtil.toString(request));
        StringView view = new StringView();

        AddCommentReplyRequest addCommentReplyRequest = new AddCommentReplyRequest();
        BeanUtils.copyProperties(request, addCommentReplyRequest);
        commentService.addCommentReply(addCommentReplyRequest);
        view.success("新增成功");

        LOGGER.info("### addCommentReply end");
        return view;
    }

    @PostMapping(value = "/deleteCommentRoot")
    @ApiOperation("删除主评论")
    public StringView deleteCommentRoot(@RequestBody IdRequest request) {
        LOGGER.info("### deleteCommentRoot start req:{}", JsonUtil.toString(request));
        StringView view = new StringView();

        commentService.deleteCommentRoot(request.getId(), SecurityUtils.getLoginUserId());
        view.success("删除成功");

        LOGGER.info("### deleteCommentRoot end");
        return view;
    }

    @PostMapping(value = "/deleteCommentReply")
    @ApiOperation("删除评论回复")
    public StringView deleteCommentReply(@RequestBody IdRequest request) {
        LOGGER.info("### deleteCommentReply start req:{}", JsonUtil.toString(request));
        StringView view = new StringView();

        commentService.deleteCommentReply(request.getId(), SecurityUtils.getLoginUserId());
        view.success("删除成功");

        LOGGER.info("### deleteCommentReply end");
        return view;
    }

    @PostMapping(value = "/queryCommentRootList")
    @ApiOperation("查询主评论列表")
    public PageListViewData<CommentRoot> queryCommentRootList(@RequestBody QueryCommentRootRequest request) {
        LOGGER.info("### queryCommentRootList start req:{}", JsonUtil.toString(request));
        PageListViewData<CommentRoot> view = new PageListViewData<>();

        QueryCommentRootCondition condition = new QueryCommentRootCondition();
        BeanUtils.copyProperties(request, condition);
        IPage<CommentRoot> commentRootIPage = commentService.queryCommentRootList(condition);

        view.setData(commentRootIPage.getRecords());
        view.setTotal((int)commentRootIPage.getTotal());

        LOGGER.info("### queryCommentRootList end");
        return view;
    }

    @PostMapping(value = "/queryCommentReplyList")
    @ApiOperation("查询评论回复列表")
    public PageListViewData<CommentReply> queryCommentReplyList(@RequestBody QueryCommentReplyRequest request) {
        LOGGER.info("### queryCommentReplyList start req:{}", JsonUtil.toString(request));
        PageListViewData<CommentReply> view = new PageListViewData<>();

        QueryCommentReplyCondition condition = new QueryCommentReplyCondition();
        BeanUtils.copyProperties(request, condition);
        IPage<CommentReply> commentReplyIPage = commentService.queryCommentReplyList(condition);

        view.setData(commentReplyIPage.getRecords());
        view.setTotal((int)commentReplyIPage.getTotal());

        LOGGER.info("### queryCommentReplyList end");
        return view;
    }

}