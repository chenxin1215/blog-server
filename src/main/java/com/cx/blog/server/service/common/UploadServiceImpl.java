package com.cx.blog.server.service.common;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cx.blog.server.dto.response.common.upload.UploadFileResp;
import com.cx.blog.server.dto.response.common.upload.UploadImageResp;
import com.cx.storage.enums.BucketEnum;
import com.cx.storage.service.IAPIGreenService;
import com.cx.storage.service.IAPIOssAccessService;
import com.cx.storage.service.IAPIOssUploadFileService;
import com.cx.storage.service.IAPIOssUploadService;
import com.cx.utils.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class UploadServiceImpl implements IUploadService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadServiceImpl.class);

    @Reference
    private IAPIOssUploadService rpcOssUploadService;

    @Reference
    private IAPIOssUploadFileService rpcOssUploadFileService;

    @Reference
    private IAPIOssAccessService rpcOssAccessService;

    @Reference
    private IAPIGreenService greenService;

    /**
     * 上传图片到oss
     *
     * @param file 图片文件
     * @return
     */
    @Override
    public UploadImageResp uploadImage(MultipartFile file, String path) {
        try (InputStream inputStream = file.getInputStream()) {
            String originalFilename = file.getOriginalFilename();
            byte[] byt = new byte[inputStream.available()];
            inputStream.read(byt);

            String imageKey;
            if (StringUtils.isBlank(path)) {
                imageKey = rpcOssUploadService.uploadImage(byt, BucketEnum.CHENXIN_BUCKET,
                    StringUtils.substringAfterLast(originalFilename, StringUtils.DOT));
            } else {
                imageKey = rpcOssUploadService.uploadImage(byt, BucketEnum.CHENXIN_BUCKET,
                    StringUtils.substringAfterLast(originalFilename, StringUtils.DOT), path);
            }

            String imageUrl = rpcOssAccessService.accessImage(imageKey, BucketEnum.CHENXIN_BUCKET, null);

            // 检查图片
            boolean sensitiveImge = greenService.isSensitiveImge(imageUrl);
            if (sensitiveImge) {
                LOGGER.error("图片审核不通过，url:{}", imageUrl);
                throw new RuntimeException("图片审核不通过");
            }

            UploadImageResp imageResp = new UploadImageResp();
            imageResp.setBucketId(BucketEnum.CHENXIN_BUCKET.getValue());
            imageResp.setImageKey(imageKey);
            imageResp.setImageUrl(imageUrl);
            return imageResp;
        } catch (IOException e) {
            LOGGER.error("图片上传失败", e);
            throw new RuntimeException("图片上传失败");
        }
    }

    /**
     * 上传文件到oss
     *
     * @param file 文件
     * @return
     */
    @Override
    public UploadFileResp uploadFile(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            String originalFilename = file.getOriginalFilename();
            byte[] byt = new byte[inputStream.available()];
            inputStream.read(byt);
            String fileKey = rpcOssUploadFileService.uploadFile(byt, BucketEnum.CHENXIN_BUCKET,
                StringUtils.substringAfterLast(originalFilename, StringUtils.DOT), null);
            String fileUrl = rpcOssAccessService.accessFile(fileKey, BucketEnum.CHENXIN_BUCKET);

            UploadFileResp fileResp = new UploadFileResp();
            fileResp.setBucketId(BucketEnum.CHENXIN_BUCKET.getValue());
            fileResp.setFileKey(fileKey);
            fileResp.setFileUrl(fileUrl);
            return fileResp;
        } catch (IOException e) {
            throw new RuntimeException("文件上传失败");
        }
    }

    @Override
    public UploadImageResp noExpireImage(MultipartFile file) {
        UploadImageResp imgResp = new UploadImageResp();

        try (InputStream inputStream = file.getInputStream()) {
            String originalFilename = file.getOriginalFilename();
            byte[] byt = new byte[inputStream.available()];
            inputStream.read(byt);
            String key = rpcOssUploadService.uploadImage(byt, BucketEnum.CHENXIN_PUBLIC_BUCKET,
                StringUtils.substringAfterLast(originalFilename, StringUtils.DOT), "public");

            imgResp.setImageKey(key);

            String imageUrl = rpcOssAccessService.accessFile(key, BucketEnum.CHENXIN_PUBLIC_BUCKET);
            imgResp.setImageUrl(imageUrl);

            // 检查图片
            boolean sensitiveImg = greenService.isSensitiveImge(imageUrl);
            if (sensitiveImg) {
                LOGGER.error("图片审核不通过，url:{}", imageUrl);
                throw new RuntimeException("图片审核不通过，请检查后重新上传");
            }
        } catch (IOException e) {
            LOGGER.error("图片上传失败", e);
            throw new RuntimeException("图片上传失败");
        }
        return imgResp;
    }
}
