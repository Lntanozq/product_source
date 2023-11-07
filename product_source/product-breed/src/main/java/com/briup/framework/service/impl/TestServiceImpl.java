package com.briup.framework.service.impl;

import com.briup.framework.mapper.TestMapper;
import com.briup.framework.service.ITestService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: product_source
 * @description: 测试
 * @author: pgc
 * @create:2022-03-22
 **/
@Service
public class TestServiceImpl implements ITestService {
    @Autowired
    TestMapper testMapper;
    @Override
    public PageInfo<String> findall() {
        // 开启分页插件,放在查询语句上面 帮助生成分页语句
        PageHelper.startPage(1,3);
        List<String> list=testMapper.findAll();
// 封装分页之后的数据  返回给客户端展示  PageInfo做了一些封装 作为一个类
        PageInfo<String> pageAttachments = new PageInfo<>(list);
        return pageAttachments;
    }
}
