package com.briup.framework.service.impl;

import com.briup.framework.bean.ManagerVaccines;
import com.briup.framework.bean.extend.ManagerBatchExtend;
import com.briup.framework.mapper.ManagerVaccinesMapper;
import com.briup.framework.mapper.extend.ManagerVaccinesExtendMapper;
import com.briup.framework.service.IManagerVaccinesService;
import com.briup.framework.util.UUIDUtil;
import com.briup.framework.utils.execption.BriupFrameworkException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @program: product_source
 * @description: 疫苗管理
 * @author: shaoyb
 * @create: 2022-03-28
 **/
@Service
public class ManagerVaccinesServiceImpl implements IManagerVaccinesService {
    @Autowired
    private ManagerVaccinesMapper managerVaccinesMapper;

    @Autowired
    private ManagerVaccinesExtendMapper managerVaccinesExtendMapper;

    @Override
    public void saveOrUpdate(ManagerVaccines managerVaccines) {
        if(managerVaccines != null) {
            //疫苗编号是否为空
            if (managerVaccines.getvVaccinesId() != null && !"".equals(managerVaccines.getvVaccinesId().trim())) {
                //编号不为空，则更新疫苗信息
                int count = managerVaccinesMapper.updateByPrimaryKeySelective(managerVaccines);
                //判断受影响的条数，判断是否更新成功
                if (count == 0) {
                    throw new BriupFrameworkException("更新失败！");
                }
            } else {
                //编号为空，则新增疫苗信息
                managerVaccines.setvVaccinesId(UUIDUtil.getUUID());
                managerVaccines.setvDelete(0);
                //managerVaccines.setvTime("2023-10-09 00:00:00");

                int count = managerVaccinesMapper.insertSelective(managerVaccines);
                //判断受影响的条数，判断是否新增成功
                if (count == 0) {
                    throw new BriupFrameworkException("保存失败！");
                }
            }
        }else{
            throw new BriupFrameworkException("参数缺失");
        }
    }

    @Override
    public void deleteById(String vId) {
        if(vId != null && !"".equals(vId.trim())) {
            ManagerVaccines mv = managerVaccinesMapper.selectByPrimaryKey(vId);
            if (mv == null)
                throw new ArithmeticException("删除失败，该疫苗不存在");

            if (mv.getvDelete() == 1)
                throw new ArithmeticException("删除失败，该疫苗已被删除");

            mv.setvDelete(1);
            managerVaccinesMapper.updateByPrimaryKeySelective(mv);
        }else {
            throw new BriupFrameworkException("参数为空");
        }
    }

    //批量删除功能实现
    @Override
    public void deleteAllByIds(List<String> vIds) {
        if(!CollectionUtils.isEmpty(vIds))
            managerVaccinesExtendMapper.deleteAllByIds(vIds);
        else
            //错误：参数为空
            throw new BriupFrameworkException("参数为空！");
    }

    @Override
    public PageInfo<ManagerVaccines> query(Integer page, Integer pageSize, String vId, String vName, String vSupplier, String vType) {
        //判断参数是否为空
        if(page != null && pageSize != null) {
            //设置分页
            PageHelper.startPage(page, pageSize);
            //条件查询
            List<ManagerVaccines> list = managerVaccinesExtendMapper.query(vId, vName, vSupplier, vType);
            PageInfo<ManagerVaccines> info = new PageInfo<>(list);
            return info;
        }else{
            throw new BriupFrameworkException("分页参数有误！");
        }
    }

    @Override
    public List<ManagerVaccines> queryAll() {
        List<ManagerVaccines> list = managerVaccinesExtendMapper.queryAll();

        return list;
    }

    @Override
    public ManagerVaccines selectById(String vId) {
        if(vId != null && !"".equals(vId.trim())){
            ManagerVaccines managerVaccines = managerVaccinesExtendMapper.selectById(vId);
            if(managerVaccines == null)
                throw new BriupFrameworkException("该疫苗不存在!");
            return managerVaccines;
        }
        throw new BriupFrameworkException("参数有误");
    }
}
