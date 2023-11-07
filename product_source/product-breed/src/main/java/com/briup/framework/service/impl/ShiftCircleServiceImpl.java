package com.briup.framework.service.impl;

import com.briup.framework.bean.ManagerAnimal;
import com.briup.framework.bean.ShiftCircle;
import com.briup.framework.bean.extend.ShiftCircleExtend;
import com.briup.framework.mapper.ShiftCircleMapper;
import com.briup.framework.mapper.extend.ManagerAnimalExtendMapper;
import com.briup.framework.mapper.extend.ShiftCircleExtendMapper;
import com.briup.framework.service.IShiftCircleService;
import com.briup.framework.utils.execption.BriupFrameworkException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @program: product_source
 * @description: 移圈记录管理
 * @author: nirui
 * @create: 2022-03-30 10:29
 */

@Service
public class ShiftCircleServiceImpl implements IShiftCircleService {
    @Autowired
    private ShiftCircleMapper shiftCircleMapper;
    @Autowired
    private ShiftCircleExtendMapper shiftCircleExtendMapper;
    @Autowired
    private ManagerAnimalExtendMapper managerAnimalExtendMapper;


    @Override
    public void saveOrUpdate(ShiftCircle shiftCircle) {
        if (shiftCircle != null) {
            //判断动物编号是否有效
            String scAnimalId = shiftCircle.getScAnimalId();
            if (scAnimalId != null) {
                ManagerAnimal managerAnimal = managerAnimalExtendMapper.selectById(scAnimalId);
                if (managerAnimal == null) {
                    throw new BriupFrameworkException("动物编号无效");
                }
                if (!managerAnimal.getaStatus().equals("养殖中")) {
                    throw new BriupFrameworkException("该动物不在养殖中");
                }
            }
            //判断移圈id是否为空
            if (shiftCircle.getScId() != null) {
                //编号不为空，则更新移圈信息
                int count = shiftCircleMapper.updateByPrimaryKeySelective(shiftCircle);
                //判断受影响的条数，判断是否更新成功
                if (count == 0) {
                    throw new BriupFrameworkException("更新失败！");
                }
            } else {
                int count = shiftCircleMapper.insertSelective(shiftCircle);
                if (count == 0) {
                    throw new BriupFrameworkException("新增失败！");
                }
            }
            if(shiftCircle.getScNewHurdlesId() != null){
                //更新动物的栏圈
                managerAnimalExtendMapper.updateHurdlesId(shiftCircle.getScAnimalId(), shiftCircle.getScNewHurdlesId());
            }
        }else{
            throw new BriupFrameworkException("参数为空");
        }

    }
    @Override
    public void deleteInBatch(List<Integer> ids) {
        //判断集合是否为空
        if(!CollectionUtils.isEmpty(ids)){
            //不为空，批量删除
            shiftCircleExtendMapper.deleteInBatch(ids);
        }else{
            //为空，参数错误
            throw new BriupFrameworkException("参数为空！");
        }
    }

    @Override
    public void deleteById(Integer id) {
        if(id != null){
            shiftCircleExtendMapper.deleteById(id);
        }else{
            throw new BriupFrameworkException("参数为空！");
        }
    }

    @Override
    public List<ShiftCircle> queryAll() {
        List<ShiftCircle> list = shiftCircleExtendMapper.queryAll();
        return list;
    }

    @Override
    public List<ShiftCircleExtend> queryAllUser() {
        List<ShiftCircleExtend> list = shiftCircleExtendMapper.queryAllUser();
        return list;
    }

    @Override
    public PageInfo<ShiftCircleExtend> query(Integer pageNum, Integer pageSize, String animalId, String ubeedId, String start, String end, String reason) {
        //判断参数是否为空
        if(pageNum != null && pageSize != null){
            //设置分页
            PageHelper.startPage(pageNum,pageSize);
            //条件查询
            List<ShiftCircleExtend> list = shiftCircleExtendMapper.query(animalId, ubeedId, start, end, reason);
            PageInfo<ShiftCircleExtend> info = new PageInfo<>(list);
            return info;
        }else{
            throw new BriupFrameworkException("参数为空！");
        }
    }


}
