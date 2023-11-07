package com.briup.framework.web;

import com.briup.framework.bean.FeedRecord;
import com.briup.framework.bean.extend.FeedRecordExtend;
import com.briup.framework.service.IFeedRecordService;
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
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author pgc
 * @Description: 饲料更换控制层
 * @date 2022/4/2 10:02
 */
@Api(tags = "饲料更换接口")
@RestController
@RequestMapping("/feedRecord")
public class FeedRecordController {
    @Autowired
    IFeedRecordService feedRecordService;
    @Permission(value="feedRecord_saveOrUpdate")
    @ApiOperation(value = "保存或更新饲料更换")
    @PostMapping("/saveOrUpdate")
    public Response<String> saveOrUpdate(@RequestBody FeedRecord feedRecord){
        feedRecordService.saveOrUpdate(feedRecord);
        return Response.success("操作成功");
    }
    @Permission(value="feedRecord_deleteById")
    @ApiOperation(value = "根据id删除饲料更换记录")
    @DeleteMapping("/deleteById/{frId}")
    public Response<String> deleteById(@PathVariable int frId) {
        feedRecordService.deleteById(frId);
        return Response.success("删除成功");
    }
    @Permission(value="feedRecord_deleteByIdAll")
    @ApiOperation(value = "批量删除饲料更换记录")
    @PostMapping("/deleteByIdAll")
    public Response<String> deleteByIdAll(@RequestBody int[] frIds) {
        for (int frId:frIds) {
            feedRecordService.deleteById(frId);
        }
        return Response.success("删除成功");
    }
    @Permission(value="feedRecord_query")
    @ApiOperation(value = "分页并根据条件查询饲料更换记录信息")
    @GetMapping("/query")
    @ApiImplicitParams({ @ApiImplicitParam(name = "page", value = "当前页",dataType = "int", required = true, paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小",dataType = "int", required = true, paramType = "query",defaultValue ="5"),
            @ApiImplicitParam(name = "frBatchId", value = "批次编号", paramType = "query"),
            @ApiImplicitParam(name = "feedName", value = "饲料名称", paramType = "query")
    })
    public Response<PageInfo<FeedRecordExtend>> query(int page, int pageSize, String frBatchId, String feedName){
        PageInfo<FeedRecordExtend> pageVM=feedRecordService.query(page,pageSize,frBatchId,feedName);
        return Response.success(pageVM);
    }
}
