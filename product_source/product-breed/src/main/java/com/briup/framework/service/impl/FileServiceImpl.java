package com.briup.framework.service.impl;

import com.briup.framework.constants.RedisConstant;
import com.briup.framework.mapper.extend.FileMapper;
import com.briup.framework.service.IFileService;
import com.briup.framework.utils.execption.BriupFrameworkException;
import com.briup.framework.utils.web.ResponseCode;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * @Auther: vanse(lc))
 * @Date: 2022/3/27-03-27-14:55
 * @Description：文件操作业务 实现
 * @version：1.0
 */
@Service
@Slf4j
public class FileServiceImpl implements IFileService {
    @Autowired
    private FastFileStorageClient storageClient;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private FileMapper fileMapper;
    /**
     * 多文件上传
     * @param files 需要上传的文件列表
     * @return 上传在fastdfs后返回的文件路径列表
     */
    //@Transactional(rollbackFor = Exception.class)
    @Override
    public List<String> uploadBatch(MultipartFile[] files) {
        //上传后得到的文件地址列表
        List<String> paths = new ArrayList<>();
        if (Objects.isNull(files) || files.length == 0) {
            throw new BriupFrameworkException("文件不能为空", ResponseCode.DATA_NOT_FOUND);
        }
        Arrays.stream(files).forEach(file -> {
            if (file.isEmpty()) {
                throw new BriupFrameworkException("文件内容不能为空", ResponseCode.DATA_WRONG);
            }
            try {
                // 上传文件
                StorePath storePath = storageClient.uploadFile("group1", file.getInputStream(), file.getSize(), getSuffix(file));
                // 拼接文件路径 返回给前端
                String path =  storePath.getFullPath();
                paths.add(path);
                // 将文件路径放到redis中 确保redis和fastdfs同步
                redisTemplate.opsForSet().add(RedisConstant.FASTDFS_IMAGES, path);
            } catch (Exception e) {
                throw new BriupFrameworkException("文件上传失败", ResponseCode.ERROR);
            }
        });
        return paths;
    }
    // 获取文件后缀
    private String getSuffix(MultipartFile file) {
        String filename = file.getOriginalFilename();
        return Objects.requireNonNull(filename).substring(filename.lastIndexOf(".") + 1);
    }

    /**
     * 暂时注释掉了该业务
     * 定时删除fastdfs中冗余的文件(解决异步上传遗留问题)
     * 每周星期天凌晨1点实行一次：0 0 1 ? * L
     * 含有图片字段的表
     *  Tmanager_feed(饲料)
     *  quarantine_registration (检疫)
     *  manager_batch(批次)
     */
    //  每5秒执行一次: */5 * * * * ?
    //@Scheduled(cron = "0 0 1 ? * 7")
//    @Transactional(rollbackFor = Exception.class)
//    public void deleteBatch() {
//        List<String> paths = fileMapper.getPathsInDB();
//        // 获取redis中存的文件路径(fastdfs中的文件名列表)
//        Set<String> fastdfsImages = redisTemplate.opsForSet().members(RedisConstant.FASTDFS_IMAGES);
//        // fastdfs(redis)中多余的图片(mysql数据库没有 redis有)
//        fastdfsImages.removeAll(paths);
//        if (!CollectionUtils.isEmpty(fastdfsImages)) {
//            // 清redis的多余值
//            redisTemplate
//                    .opsForSet()
//                    .remove(RedisConstant.FASTDFS_IMAGES, fastdfsImages.toArray(new String[fastdfsImages.size()]));
//            fastdfsImages
//                    .stream()
//                    .forEach(n -> {
//                        storageClient.deleteFile(n);
//                    });
//
//        }
//       log.info("每周星期天凌晨1点清理服务器冗余图片");
//    }
}
