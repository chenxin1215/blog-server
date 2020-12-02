package com.cx.blog.server.dto.response.common.upload;

import java.io.Serializable;

public class UploadFileResp implements Serializable {

    private static final long serialVersionUID = -6584222270568392910L;

    /**
     * 详见{@link com.cx.storage.aliyun.enums.BucketEnum}
     */
    private Integer bucketId;

    /**
     * 文件oss key
     */
    private String fileKey;

    /**
     * 文件访问地址
     */
    private String fileUrl;

    public Integer getBucketId() {
        return bucketId;
    }

    public void setBucketId(Integer bucketId) {
        this.bucketId = bucketId;
    }

    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
