package com.briup.framework.service.impl;

import com.briup.framework.bean.ManagerCustomer;
import com.briup.framework.bean.extend.ManagerCustomerExtend;
import com.briup.framework.mapper.ManagerCustomerMapper;
import com.briup.framework.mapper.extend.ManagerCustomerExtendMapper;
import com.briup.framework.service.IManagerCustomerService;
import com.briup.framework.utils.execption.BriupFrameworkException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @program: product_source
 * @description: 客户管理
 * @author: nirui
 * @create: 2022-03-29 10:17
 */
@Service
public class ManagerCustomerServiceImpl implements IManagerCustomerService {
    @Autowired
    private ManagerCustomerMapper managerCustomerMapper;
    @Autowired
    private ManagerCustomerExtendMapper managerCustomerExtendMapper;

    @Override
    public void saveOrUpdate(ManagerCustomer managerCustomer) {
        if(managerCustomer != null) {
            //判断客户id是否为空
            if (managerCustomer.getcId()!=null) {
                //编号不为空，则更新客户信息
                int count = managerCustomerMapper.updateByPrimaryKeySelective(managerCustomer);
                //判断受影响的条数，判断是否更新成功
                if (count == 0) {
                    throw new BriupFrameworkException("更新失败！");
                }
            } else {
                //编号为空，则新增客户信息
                managerCustomer.setcDelete(0);
                int count = managerCustomerMapper.insertSelective(managerCustomer);
                //判断受影响的条数，判断是否新增成功
                if (count == 0) {
                    throw new BriupFrameworkException("保存失败！");
                }
            }
        }else{
            throw  new BriupFrameworkException("参数缺失");
        }
    }

    @Override
    public void deleteInBatch(List<Integer> ids) {
        //判断集合是否为空
        if(!CollectionUtils.isEmpty(ids)){
            //不为空，批量删除
            managerCustomerExtendMapper.deleteInBatch(ids);
        }else{
            //为空，参数错误
            throw new BriupFrameworkException("参数为空！");
        }
    }

    @Override
    public void deleteById(Integer id) {
        //判断id是否为空
        if(id != null){
            //id不为空，删除客户信息
            managerCustomerExtendMapper.deleteById(id);
        }else{
            throw new BriupFrameworkException("参数为空！");
        }
    }

    @Override
    public List<ManagerCustomer> queryAll() {
        List<ManagerCustomer> list = managerCustomerExtendMapper.queryAll();
        return list;
    }

    @Override
    public PageInfo<ManagerCustomerExtend> query(Integer pageNum, Integer pageSize, String company, String name, String usaleId, String level) {
        //判断参数是否为空
        if(pageNum != null && pageSize != null){
            //设置分页
            PageHelper.startPage(pageNum,pageSize);
            //条件查询
            List<ManagerCustomerExtend> list = managerCustomerExtendMapper.query(company, name, usaleId, level);
            PageInfo<ManagerCustomerExtend> info = new PageInfo<>(list);
            return info;
        }else{
            throw new BriupFrameworkException("参数为空！");
        }

    }

    @Override
    public List<ManagerCustomerExtend> queryAllUser() {
        List<ManagerCustomerExtend> list = managerCustomerExtendMapper.queryAllUser();
        return list;
    }


}
