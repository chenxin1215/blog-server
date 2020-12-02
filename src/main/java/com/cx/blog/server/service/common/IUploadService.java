package com.cx.blog.server.service.common;

import org.springframework.web.multipart.MultipartFile;

import com.cx.blog.server.dto.response.common.upload.UploadFileResp;
import com.cx.blog.server.dto.response.common.upload.UploadImageResp;

/**
 * @author liangranhui
 * @date 2020/6/24
 */
public interface IUploadService {

    /**
     * 上传图片到oss
     *
     * @param file 图片文件
     * @return
     */
    UploadImageResp uploadImage(MultipartFile file, String path);

    /**
     * 上传文件到oss
     *
     * @param file 文件
     * @return
     */
    UploadFileResp uploadFile(MultipartFile file);

    /**
     * 功能描述: 上传不过期的图片
     *
     * @Author: chenxin
     * @Param: [file]
     * @Date: 2020/9/4
     */
    UploadImageResp noExpireImage(MultipartFile file);
}
