package com.briup.framework.service.impl;

import com.briup.framework.bean.ManagerDisease;
import com.briup.framework.bean.ManagerVaccines;
import com.briup.framework.mapper.ManagerDiseaseMapper;
import com.briup.framework.mapper.extend.ManagerDiseaseExtendMapper;
import com.briup.framework.service.IManagerDiseaseService;
import com.briup.framework.util.UUIDUtil;
import com.briup.framework.utils.execption.BriupFrameworkException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @program: product_source
 * @description: 病症管理
 * @author: shaoyb
 * @create: 2022-03-28
 **/
@Service
public class ManagerDiseaseServiceImpl implements IManagerDiseaseService {
    @Autowired
    private ManagerDiseaseMapper managerDiseaseMapper;
    @Autowired
    private ManagerDiseaseExtendMapper managerDiseaseExtendMapper;

    @Override
    public void saveOrUpdate(ManagerDisease managerDisease) {
        if(managerDisease == null)
            throw new ArithmeticException("参数为空");

        if(managerDisease.getdId() != null) {
            //更新操作
            int count = managerDiseaseMapper.updateByPrimaryKeySelective(managerDisease);
            if(count == 0)
                throw new ArithmeticException("更新失败");
        }else {
            //插入操作
            managerDisease.setdDelete(0);
            managerDiseaseMapper.insertSelective(managerDisease);
        }
    }

    @Override
    public void deleteById(Integer dId) {
        if(dId != null) {
            ManagerDisease managerDisease = managerDiseaseMapper.selectByPrimaryKey(dId);
            if (managerDisease == null)
                throw new ArithmeticException("删除失败，该病症不存在");

            if (managerDisease.getdDelete() == 1)
                throw new ArithmeticException("删除失败，该病症已被删除");

            managerDisease.setdDelete(1);

            //UpdateByprimarykey 全表覆盖更新。
            //UpdateByprimarykeySelective 忽略为null的值，只更新不为null的字段。
            managerDiseaseMapper.updateByPrimaryKeySelective(managerDisease);
        }else {
            throw new BriupFrameworkException("参数为空");
        }
    }

    @Override
    public void deleteAllByIds(List<Integer> dIds) {
        if(CollectionUtils.isEmpty(dIds))
            throw new BriupFrameworkException("参数为空");

        managerDiseaseExtendMapper.deleteAllByIds(dIds);
    }

    @Override
    public PageInfo<ManagerDisease> query(Integer page, Integer pageSize, String dName, String dType) {
        if(page == null || pageSize == null)
            throw new BriupFrameworkException("分页参数为空");

        PageHelper.startPage(page,pageSize);
        List<ManagerDisease> list = managerDiseaseExtendMapper.query(dName,dType);
        PageInfo<ManagerDisease> pageManagerDisease= new PageInfo<>(list);
        return pageManagerDisease;
    }

    @Override
    public List<ManagerDisease> queryAll() {
        return managerDiseaseExtendMapper.queryAll();
    }

    @Override
    public ManagerDisease selectById(Integer diseaseId) {
        if(diseaseId != null) {
            ManagerDisease managerDisease = managerDiseaseExtendMapper.selectById(diseaseId);
            if(managerDisease == null)
                throw new BriupFrameworkException("参数有误，查找失败");

            return managerDisease;
        }else{
            throw new BriupFrameworkException("参数为空");
        }
    }
}
