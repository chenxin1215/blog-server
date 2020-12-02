package com.cx.blog.server.dto.response.common.upload;

import java.io.Serializable;

public class UploadImageResp implements Serializable {

    private static final long serialVersionUID = -1277545886244196869L;

    /**
     * 详见{@link com.cx.storage.aliyun.enums.BucketEnum}
     */
    private Integer bucketId;

    /**
     * 图片oss key
     */
    private String imageKey;

    /**
     * 图片访问地址
     */
    private String imageUrl;

    public Integer getBucketId() {
        return bucketId;
    }

    public void setBucketId(Integer bucketId) {
        this.bucketId = bucketId;
    }

    public String getImageKey() {
        return imageKey;
    }

    public void setImageKey(String imageKey) {
        this.imageKey = imageKey;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
