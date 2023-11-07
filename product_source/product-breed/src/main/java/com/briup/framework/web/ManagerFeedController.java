package com.briup.framework.web;

import com.briup.framework.base.common.logging.annotation.LoggingAccess;
import com.briup.framework.bean.ManagerFeed;
import com.briup.framework.bean.dto.ManagerFeedDto;
import com.briup.framework.service.IManagerFeedService;
import com.briup.framework.utils.security.Permission;
import com.briup.framework.utils.web.Response;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @Auther: vanse(lc))
 * @Date: 2022/3/27-03-27-18:58
 * @Description： 饲料管理接口
 * @version：1.0
 */
@Api(tags = "饲料管理接口")
@RestController
@RequestMapping("/feed")
@LoggingAccess("管理模块")
public class ManagerFeedController {
    @Autowired
    private IManagerFeedService managerFeedService;
    @Permission(value="feed_saveOrUpdate")
    @PostMapping("/saveOrUpdate")
    @ApiOperation(value = "添加或修改饲料", notes = "传参注释在model中查看,需要json格式")
    @LoggingAccess("添加或饲料")
    public Response addFeed(@RequestBody ManagerFeedDto feed) {
        managerFeedService.saveOrUpdate(feed);
        return Response.success();
    }

    @Permission(value="feed_query")
    @GetMapping("/query")
    @ApiOperation(value = "查询饲料", notes = "分页并根据条件查询")
    @LoggingAccess("查询饲料")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码",dataType = "int", required = true,defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数",dataType = "int", required = true,defaultValue = "5"),
            @ApiImplicitParam(name = "fName",value = "饲料名称",paramType = "query"),
            @ApiImplicitParam(name = "fSupplier",value = "饲料供应商",paramType = "query"),
            @ApiImplicitParam(name = "fType",value = "饲料类型",paramType = "query")
    })
    public Response<PageInfo<ManagerFeed> > searchFeed(int page, int pageSize, String fName, String fSupplier, String fType) {
        return Response.success(managerFeedService.searchFeed(page,pageSize,fName,fSupplier,fType));
    }
    @Permission(value="feed_queryAll")
    @GetMapping("/queryAll")
    @ApiOperation(value = "查询饲料")
    @LoggingAccess("查询饲料")
    public Response<List<ManagerFeed> > queryAll() {
        return Response.success(managerFeedService.queryAll());
    }
    @Permission(value="feed_deleteById")
    @DeleteMapping("/deleteById/{id}")
    @ApiOperation(value = "删除饲料", notes = "rest风格")
    @LoggingAccess("删除饲料")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "饲料id", paramType = "path",dataType = "int")
    })
    public Response deleteFeed(@PathVariable Integer id) {
        managerFeedService.deleteFeed(id);
        return Response.success();
    }
    @Permission(value="feed_deleteByIdAll")
    @DeleteMapping("/deleteByIdAll")
    @ApiOperation(value = "批量删除饲料", notes = "json传参")
    @LoggingAccess("批量删除饲料")
    public Response batchDeleteFeed(@RequestParam("ids") @ApiParam("饲料id数组") Integer[] ids) {
        managerFeedService.batchDeleteFeed(Arrays.asList(ids));
        return Response.success();
    }

}
