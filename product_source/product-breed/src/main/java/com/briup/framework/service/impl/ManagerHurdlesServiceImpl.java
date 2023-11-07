package com.briup.framework.service.impl;

import com.briup.framework.bean.ManagerHurdles;
import com.briup.framework.bean.extend.ManagerHurdlesExtend;
import com.briup.framework.mapper.ManagerHurdlesMapper;
import com.briup.framework.mapper.extend.ManagerHurdlesExtendMapper;
import com.briup.framework.service.IManagerHurdlesService;
import com.briup.framework.util.UUIDUtil;
import com.briup.framework.utils.execption.BriupFrameworkException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: product_source
 * @description: 栏圈管理
 * @author: pgc
 * @create:2022-03-23
 **/
@Service
public class ManagerHurdlesServiceImpl implements IManagerHurdlesService {
    @Autowired
    private ManagerHurdlesMapper managerHurdlesMapper;
    @Autowired
    private ManagerHurdlesExtendMapper managerHurdlesExtendMapper;

    @Override
    public void saveOrUpdate(ManagerHurdles managerHurdles) {
         if (managerHurdles.gethId()!=null&&!managerHurdles.gethId().equals("")){
             //更新
             int count=managerHurdlesMapper.updateByPrimaryKeySelective(managerHurdles);
             if (count==0){
                 throw new BriupFrameworkException("更新失败");
             }
         }else {
             //插入
             managerHurdles.sethId(UUIDUtil.getUUID());
             managerHurdles.sethSaved(0);
             //managerHurdles.sethTime(DateTimeUtil.DateToStr(new Date()));
             managerHurdles.sethFull("未满");
             managerHurdles.sethDelete(0);
            int count=managerHurdlesMapper.insertSelective(managerHurdles);
             if (count==0){
                 throw new BriupFrameworkException("插入失败");
             }
         }
    }

    @Override
    public void deleteById(String hId) {
        ManagerHurdles managerHurdles = managerHurdlesMapper.selectByPrimaryKey(hId);
        if (managerHurdles==null){
            throw new BriupFrameworkException("删除失败");
        }
        managerHurdlesExtendMapper.updateById(hId);
    }

    @Override
    public PageInfo<ManagerHurdlesExtend> query(int page, int pageSize, String hName, String hMax,String fhName,String hEnable) {
        // 开启分页插件,放在查询语句上面 帮助生成分页语句
        PageHelper.startPage(page,pageSize);
        List<ManagerHurdlesExtend> list=managerHurdlesExtendMapper.query(hName,hMax,fhName,hEnable);
        PageInfo<ManagerHurdlesExtend> managerHurdlesExtendPageInfo=new PageInfo<>(list);
        return managerHurdlesExtendPageInfo;
    }

    @Override
    public ManagerHurdlesExtend selectById(String hId) {
        return null;
    }

    @Override
    public List<Integer> queryAllMax() {
        return managerHurdlesExtendMapper.queryAllMax();
    }

    @Override
    public void updateByIdSaved(String hId) {
        managerHurdlesExtendMapper.updateByIdSaved(hId);
    }

    @Override
    public List<ManagerHurdlesExtend> selectAllHurdles() {
        return  managerHurdlesExtendMapper.query(null,null,null,null);
    }
}
