package com.briup.framework.vanse.upload;

import com.briup.framework.ProductSourceApplication;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @Auther: vanse(lc))
 * @Date: 2022/3/27-03-27-14:32
 * @Description：fastdfs api测试
 * @version：1.0
 */
@SpringBootTest(classes = ProductSourceApplication.class)
public class TestUpload {
    @Autowired
    private FastFileStorageClient storageClient;
    @Test
    public void testUploadFile(){
        File file = new File("C://Users//vanse/Pictures/Camera Roll/2.jpg");
        try {
            FileInputStream in = new FileInputStream(file);
            // 文件上传可以重复 因为fastdfs中的文件是随机生成的
            StorePath storePath = storageClient.uploadFile("group1",in, file.length(), ".jpg");
           // http://192.168.23.160:9527/group1/M00/00/00/wKgXoGI_JNKAdGEPAAZX66nVt9s25..jpg
            System.out.println("http://121.224.77.1:34883/"+storePath.getFullPath());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testDeleteFile(){
        // 文件名不存在会报错 http://192.168.23.160:9527/
        storageClient.deleteFile("group1/M00/00/00/wKgXoWJEN7WAbMSZAA0d4KKcBBI66..jpg");
    }
}
