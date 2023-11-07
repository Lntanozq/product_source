package com.briup.framework.web;

import com.briup.framework.base.common.logging.annotation.LoggingAccess;
import com.briup.framework.service.IFileService;
import com.briup.framework.utils.security.Permission;
import com.briup.framework.utils.web.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @Auther: vanse(lc))
 * @Date: 2022/3/27-03-27-14:43
 * @Description： 图片上传(fastdfs)
 * @version：1.0
 */
@Api(tags = "图片上传接口")
@RestController
@RequestMapping("/file")
@LoggingAccess("文件上传模块")
public class FileController {
    @Autowired
    private IFileService fileService;
    @Permission(value="file_upload")
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    @ApiOperation("文件批量上传支fastdfs服务器")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "files", value = "文件", paramType = "form", dataType = "__file", allowMultiple = true)
    )
    @LoggingAccess("文件上传")
    public Response<List<String>> upload(@RequestParam("files") MultipartFile[] files) {
        return Response.success(fileService.uploadBatch(files));
    }

}
