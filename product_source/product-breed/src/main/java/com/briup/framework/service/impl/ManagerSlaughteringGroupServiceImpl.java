package com.briup.framework.service.impl;

import com.briup.framework.base.bean.basic.BaseAccount;
import com.briup.framework.base.mapper.basic.BaseAccountMapper;
import com.briup.framework.bean.GroupUser;
import com.briup.framework.bean.ManagerSlaughteringGroup;
import com.briup.framework.bean.extend.ManagerSlaughteringGroupExtend;
import com.briup.framework.mapper.GroupUserMapper;
import com.briup.framework.mapper.ManagerSlaughteringGroupMapper;
import com.briup.framework.mapper.extend.GroupUserExtendMapper;
import com.briup.framework.mapper.extend.ManagerSlaughteringGroupExtendMapper;
import com.briup.framework.mapper.extend.UserExtendMapper;
import com.briup.framework.service.IManagerSlaughteringGroupService;
import com.briup.framework.utils.execption.BriupFrameworkException;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.basic.BasicRadioButtonMenuItemUI;
import javax.xml.ws.Response;
import java.util.List;

/**
 * @program: product_source
 * @description 屠宰组业务逻辑
 * @author shaoyb
 * @create 2022/3/29 19:53
 **/
@Service
public class ManagerSlaughteringGroupServiceImpl implements IManagerSlaughteringGroupService {

    @Autowired
    private ManagerSlaughteringGroupMapper managerSlaughteringGroupMapper;
    @Autowired
    private ManagerSlaughteringGroupExtendMapper managerSlaughteringGroupExtendMapper;
    @Autowired
    private GroupUserMapper groupUserMapper;
    @Autowired
    private GroupUserExtendMapper groupUserExtendMapper;
    @Autowired
    private UserExtendMapper userExtendMapper;

    @Override
    public void saveOrUpdate(ManagerSlaughteringGroup slaughteringGroup) {
        if(slaughteringGroup == null)
            throw new BriupFrameworkException("参数缺失");

        int count = 0;
        if(slaughteringGroup.getSgId() != null){
            //更新基本信息
            count = managerSlaughteringGroupMapper.updateByPrimaryKeySelective(slaughteringGroup);
            if (count == 0) {
                throw new BriupFrameworkException("更新失败！");
            }
        }else{
            //新增屠宰组
            slaughteringGroup.setSgDelete(0);
            count = managerSlaughteringGroupMapper.insertSelective(slaughteringGroup);
            if (count == 0) {
                throw new BriupFrameworkException("新增失败！");
            }
        }
    }

    @Override
    public void updateSlaughteringGroupAccount(Integer sgId, String[] accountIds, Integer sgNum) {
        //1.参数验证
        if(sgId == null || sgNum == null || sgNum <= 0 || accountIds == null || accountIds.length == 0)
            throw new BriupFrameworkException("参数有误");

        ManagerSlaughteringGroup slaughteringGroup = managerSlaughteringGroupMapper.selectByPrimaryKey(sgId);
        if(slaughteringGroup == null)
            throw new BriupFrameworkException("屠宰组id有误");

        //2.更新屠宰组 中 人员数量
        slaughteringGroup.setSgNum(accountIds.length); //此处应该 sgNum == accountIds.length
        managerSlaughteringGroupMapper.updateByPrimaryKeySelective(slaughteringGroup);

        //3.往屠宰组人员构成表中添加数据
        //3.1 根据屠宰组id删除groupUser表中所有相关数据
        groupUserExtendMapper.deleteBySGroupId(sgId);

        //3.2 准备一个bean对象,并设置确定的 屠宰组id值
        GroupUser groupUser = new GroupUser();
        groupUser.setGuGroupId(sgId);

        //3.3 遍历人员数组，往bean对象中设置 人员id
        for (String aId : accountIds) {
            groupUser.setGuSlaughterId(aId);
            //3.3 逐条记录插入
            groupUserMapper.insertSelective(groupUser);
        }
    }

    @Override
    public void deleteSlaughteringGroupBySgId(Integer sgId) {
        //1.参数判断
        if(sgId == null)
            throw new BriupFrameworkException("参数为空");

        ManagerSlaughteringGroup slaughteringGroup = managerSlaughteringGroupMapper.selectByPrimaryKey(sgId);
        if(slaughteringGroup == null)
            throw new BriupFrameworkException("屠宰组id有误");

        //2.删除屠宰组
        //2.1 更新manager_slaughtering_group中 delete字段为1,成员数量为0
        slaughteringGroup.setSgDelete(1);
        managerSlaughteringGroupMapper.updateByPrimaryKeySelective(slaughteringGroup);

        //2.2 删除group_user表中 符合条件的记录
        groupUserExtendMapper.deleteBySGroupId(sgId);
    }

    @Override
    public void deleteAllSlaughteringGroupBySgIds(List<Integer> sgIds) {
        //1.参数判断
        if(sgIds == null || sgIds.isEmpty())
            throw new BriupFrameworkException("参数为空");

        //2.删除多个屠宰组
        //2.1 更新manager_slaughtering_group中 delete字段为1
        managerSlaughteringGroupExtendMapper.deleteAllByIds(sgIds);

        //2.2 删除group_user表中 符合条件的记录
        groupUserExtendMapper.deleteAllBySGroupIds(sgIds);
    }

    @Override
    public List<ManagerSlaughteringGroupExtend> queryAll() {
        List<ManagerSlaughteringGroupExtend> list =  managerSlaughteringGroupExtendMapper.selectAllSGroupWithAccounts();
        if(list == null)
            throw new BriupFrameworkException("查询失败");
        return list;
    }

    @Override
    public PageInfo<ManagerSlaughteringGroupExtend> query(Integer page, Integer pageSize, String sgName, Integer sgNum) {
        //1.分页参数判断
        if (page == null || pageSize == null)
            throw new BriupFrameworkException("参数为空");

        //2.开启分页查询
        PageHelper.startPage(page, pageSize);

        //3.执行模糊查询
        List<ManagerSlaughteringGroupExtend> list = managerSlaughteringGroupExtendMapper.query(sgName, sgNum);
        System.out.println("list.size: " + list.size());

        //4.封装成分页对象并返回
        PageInfo<ManagerSlaughteringGroupExtend> info = new PageInfo<>(list);

        return info;
    }

    /*
    @Override
    public PageInfo<ManagerSlaughteringGroupExtend> query2(Integer page, Integer pageSize, String sgName, Integer sgNum) {
        //1.分页参数判断
        if(page == null || pageSize == null)
            throw new BriupFrameworkException("参数为空");

        //2.开启分页查询
        PageHelper.startPage(page,pageSize);

        //3.执行模糊查询
        List<ManagerSlaughteringGroupExtend> list = managerSlaughteringGroupExtendMapper.query(sgName,sgNum);
        System.out.println("list.size: " + list.size());

        //自定义total

        //4.封装成分页对象并返回
        PageInfo<ManagerSlaughteringGroupExtend> info = new PageInfo<>();
        //一共5条  每页3个  5/3 = 1 + 1
        int size = list.size();
        //总页数
        int totalPage = size / pageSize;
        if(size % pageSize != 0)
            totalPage++;
        info.setPages(totalPage);   //一共多少页
        info.setList(list);         //设置list成员
        info.setTotal(list.size()); //总个数
        //设置当前页数量
        if(totalPage > page)
            info.setSize(pageSize);
        else
            info.setSize(size - pageSize*(totalPage-1));
        info.setPageSize(pageSize); //每页条数
        info.setPageNum(page);      //设置当前页

        if(page >= totalPage) {
            info.setPageNum(totalPage);      //当前页
            info.setHasNextPage(false);
            info.setIsLastPage(true);
        }else{
            info.setNextPage(page+1);
            info.setHasNextPage(true);
            info.setIsLastPage(false);
        }

        if(info.getPageNum() > 1){
            info.setHasPreviousPage(true);
            info.setPrePage(info.getPageNum()-1);
            info.setIsFirstPage(false);
        }else{
            info.setIsFirstPage(true);
            info.setHasPreviousPage(false);
        }
        //System.out.println("total: " + info.getTotal());

        return info;
    }
    */

    @Override
    public ManagerSlaughteringGroupExtend selectById(Integer sgId) {
        if(sgId == null)
            throw new BriupFrameworkException("参数为空");

        ManagerSlaughteringGroup sGroup = managerSlaughteringGroupMapper.selectByPrimaryKey(sgId);
        if(sGroup == null)
            throw new BriupFrameworkException("参数无效");

        ManagerSlaughteringGroupExtend sGroupExtend =
                managerSlaughteringGroupExtendMapper.selectSGroupWithAccountsById(sgId);

        return sGroupExtend;
    }

    @Override
    public List<BaseAccount> queryAllSlaughteringAccount() {
        List<BaseAccount> list = userExtendMapper.query("8d236e4f-3d82-4439-9b0d-c5bd475ac9e8");
        return list;
    }
}
