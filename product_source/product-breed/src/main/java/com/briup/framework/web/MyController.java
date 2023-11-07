package com.briup.framework.web;

import com.briup.framework.service.ITestService;
import com.briup.framework.utils.security.Permission;
import com.briup.framework.utils.web.Response;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;


/**
 * @Author guomiao
 * @Date 2022/3/11 9:12
 */
@Api(tags = "Test测试服务")
@RestController
@RequestMapping("/test")
public class MyController {

    @Autowired
    private ITestService testService;

    @Permission("aaaa")
    @GetMapping("/sayHello")
    public Response< PageInfo<String>> hello(){
        PageInfo<String> all=testService.findall();
        System.out.println("list : "+all);
        return Response.success(all);
    }
}
