package com.briup.framework.web;

import com.briup.framework.bean.extend.ProductSource;
import com.briup.framework.constants.IPConstant;
import com.briup.framework.service.IFileService;
import com.briup.framework.service.IProjectSourceService;
import com.briup.framework.util.FileUtil;
import com.briup.framework.utils.security.Permission;
import com.briup.framework.utils.web.Response;
import com.briup.framework.zxing.QRCodeUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author pgc
 * @Description: 二维码生成及溯源
 * @date 2022/4/8 11:20
 */
@Api(tags = "二维码接口")
@RestController
@RequestMapping("/nologin")
public class ProductSourceController {
    @Autowired
    private IFileService fileService;
    @Autowired
    private IProjectSourceService projectSourceService;
    @Permission(value="nologin_product")
    @ApiOperation(value = "生成二维码")
    @GetMapping("/product")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "animalId", value = "动物编号", paramType = "query",required = true)
    })
    public Response<String> product(String animalId){
        // 存放在二维码中的内容
        String text = IPConstant.IMG_QR_CODE+"BriupPtsSource?animalId="+animalId;
        // 生成的二维码的路径及名称
        //String destPath = "E:/项目/00农产品溯源/"+animalId+".jpg";
        String destPath = "/www/ncp/"+animalId+".jpg";
        List<String> list=null;
        //生成二维码
        try {
            QRCodeUtil.encode(text, destPath);
            File file = new File(destPath);
            FileInputStream inputStream =  new FileInputStream(file);
            MultipartFile multipartFile = FileUtil.getMultipartFile(file);
            MultipartFile[] files=new MultipartFile[1];
             files[0]=multipartFile;
             list=fileService.uploadBatch(files);
             projectSourceService.updateAnimalImg(animalId,IPConstant.IMG_SERVER+list.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.success(list.get(0));
    }
    @Permission(value="nologin_redirect")
    @ApiOperation(value = "二维码跳转")
    @GetMapping("/redirect")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "animalId", value = "动物编号", paramType = "query",required = true)
    })
    public Response<ProductSource> redirect(String animalId){
        System.out.println("哈哈2:"+animalId);
        //溯源内容
        ProductSource source = projectSourceService.query(animalId);
        return Response.success(source);
    }
}
