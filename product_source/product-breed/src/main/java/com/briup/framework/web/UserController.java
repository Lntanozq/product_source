package com.briup.framework.web;

import com.briup.framework.base.bean.basic.BaseAccount;
import com.briup.framework.service.IUserService;
import com.briup.framework.utils.security.Permission;
import com.briup.framework.utils.web.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @program: product_source
 * @description: 用户角色控制层
 * @author: pgc
 * @create:2022-03-29
 **/
@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;
    @Permission(value="user_selectByRoleId")
    @ApiOperation(value = "根据角色id查询用户信息")
    @GetMapping("/selectByRoleId")
    public Response<List<BaseAccount>> selectByRoleId(String roleId) {
        List<BaseAccount> list = userService.query(roleId);
        return Response.success(list);
    }
}
