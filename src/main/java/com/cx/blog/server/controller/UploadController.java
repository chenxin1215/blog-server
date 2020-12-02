package com.cx.blog.server.controller;

import com.cx.blog.server.commom.FilePathConstants;
import com.cx.blog.server.dto.response.common.SimpleView;
import com.cx.blog.server.dto.response.common.upload.UploadFileResp;
import com.cx.blog.server.dto.response.common.upload.UploadImageResp;
import com.cx.blog.server.service.common.IUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/upload")
@Api("文件上传接口")
public class UploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private IUploadService uploadService;

    /**
     * 上传头像图片
     *
     * @param file
     * @return
     */
    @PostMapping("/uploadHeadProfileImage")
    @ApiOperation("上传头像图片")
    public SimpleView<UploadImageResp> uploadHeadProfileImage(@RequestParam(value = "file") MultipartFile file) {
        SimpleView<UploadImageResp> resp = new SimpleView<>();
        resp.success(uploadService.uploadImage(file, FilePathConstants.HEAD_IMAGE_PATH));
        return resp;
    }

    /**
     * 上传图片
     *
     * @param file
     * @return
     */
    @PostMapping("/uploadImage")
    @ApiOperation("上传图片")
    public SimpleView<UploadImageResp> uploadImage(@RequestParam(value = "file") MultipartFile file) {
        SimpleView<UploadImageResp> resp = new SimpleView<>();
        resp.success(uploadService.uploadImage(file, FilePathConstants.COMMON_IMAGE_PATH));
        return resp;
    }

    @PostMapping("/uploadImageList")
    @ApiOperation("上传图片列表")
    public SimpleView<List<UploadImageResp>> uploadImageList(@RequestParam(value = "files") List<MultipartFile> files) {
        SimpleView<List<UploadImageResp>> resp = new SimpleView<>();
        List<UploadImageResp> respList = new ArrayList<>();

        for (MultipartFile file : files) {
            UploadImageResp uploadImageResp = uploadService.uploadImage(file, FilePathConstants.COMMON_IMAGE_PATH);
            respList.add(uploadImageResp);
        }

        resp.success(respList);
        return resp;
    }

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @PostMapping("/uploadFile")
    @ApiOperation("上传文件")
    public SimpleView<UploadFileResp> uploadFile(@RequestParam(value = "file") MultipartFile file) {
        LOGGER.info("uploadImage start :{}", file);

        SimpleView<UploadFileResp> resp = new SimpleView<>();
        resp.success(uploadService.uploadFile(file));

        LOGGER.info("uploadImage end");
        return resp;
    }

    @PostMapping("/noExpireImage")
    @ApiOperation(value = "不过期图片上传接口", notes = "返回的图片访问路径需拼接图片访问主机地址才可访问")
    public SimpleView<UploadImageResp> noExpireImage(@RequestParam(value = "file") MultipartFile file) {
        LOGGER.info("noExpireImage start :{}", file);

        SimpleView<UploadImageResp> resp = new SimpleView<>();
        resp.success(uploadService.noExpireImage(file));

        LOGGER.info("noExpireImage end");
        return resp;
    }

}
