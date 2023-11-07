package com.briup.framework.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Auther: vanse(lc))
 * @Date: 2022/3/27-03-27-14:49
 * @Description：文件上传相关业务
 * @version：1.0
 */
public interface IFileService {
    List<String> uploadBatch(MultipartFile[] files);
}
