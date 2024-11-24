package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysContactMapper;
import com.ruoyi.system.domain.SysContact;
import com.ruoyi.system.service.ISysContactService;

/**
 * 联系人管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-11-24
 */
@Service
public class SysContactServiceImpl implements ISysContactService 
{
    @Autowired
    private SysContactMapper sysContactMapper;

    /**
     * 查询联系人管理
     * 
     * @param id 联系人管理主键
     * @return 联系人管理
     */
    @Override
    public SysContact selectSysContactById(Long id)
    {
        return sysContactMapper.selectSysContactById(id);
    }

    /**
     * 查询联系人管理列表
     * 
     * @param sysContact 联系人管理
     * @return 联系人管理
     */
    @Override
    public List<SysContact> selectSysContactList(SysContact sysContact)
    {
        return sysContactMapper.selectSysContactList(sysContact);
    }

    /**
     * 新增联系人管理
     * 
     * @param sysContact 联系人管理
     * @return 结果
     */
    @Override
    public int insertSysContact(SysContact sysContact)
    {
        sysContact.setCreateTime(DateUtils.getNowDate());
        return sysContactMapper.insertSysContact(sysContact);
    }

    /**
     * 修改联系人管理
     * 
     * @param sysContact 联系人管理
     * @return 结果
     */
    @Override
    public int updateSysContact(SysContact sysContact)
    {
        sysContact.setUpdateTime(DateUtils.getNowDate());
        return sysContactMapper.updateSysContact(sysContact);
    }

    /**
     * 批量删除联系人管理
     * 
     * @param ids 需要删除的联系人管理主键
     * @return 结果
     */
    @Override
    public int deleteSysContactByIds(Long[] ids)
    {
        return sysContactMapper.deleteSysContactByIds(ids);
    }

    /**
     * 删除联系人管理信息
     * 
     * @param id 联系人管理主键
     * @return 结果
     */
    @Override
    public int deleteSysContactById(Long id)
    {
        return sysContactMapper.deleteSysContactById(id);
    }
}
