package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysUserContactMapper;
import com.ruoyi.system.domain.SysUserContact;
import com.ruoyi.system.service.ISysUserContactService;

/**
 * 用户和联系人关联Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-11-24
 */
@Service
public class SysUserContactServiceImpl implements ISysUserContactService 
{
    @Autowired
    private SysUserContactMapper sysUserContactMapper;

    /**
     * 查询用户和联系人关联
     * 
     * @param userId 用户和联系人关联主键
     * @return 用户和联系人关联
     */
    @Override
    public SysUserContact selectSysUserContactByUserId(Long userId)
    {
        return sysUserContactMapper.selectSysUserContactByUserId(userId);
    }

    /**
     * 查询用户和联系人关联列表
     * 
     * @param sysUserContact 用户和联系人关联
     * @return 用户和联系人关联
     */
    @Override
    public List<SysUserContact> selectSysUserContactList(SysUserContact sysUserContact)
    {
        return sysUserContactMapper.selectSysUserContactList(sysUserContact);
    }

    /**
     * 新增用户和联系人关联
     * 
     * @param sysUserContact 用户和联系人关联
     * @return 结果
     */
    @Override
    public int insertSysUserContact(SysUserContact sysUserContact)
    {
        return sysUserContactMapper.insertSysUserContact(sysUserContact);
    }

    /**
     * 修改用户和联系人关联
     * 
     * @param sysUserContact 用户和联系人关联
     * @return 结果
     */
    @Override
    public int updateSysUserContact(SysUserContact sysUserContact)
    {
        return sysUserContactMapper.updateSysUserContact(sysUserContact);
    }

    /**
     * 批量删除用户和联系人关联
     * 
     * @param userIds 需要删除的用户和联系人关联主键
     * @return 结果
     */
    @Override
    public int deleteSysUserContactByUserIds(Long[] userIds)
    {
        return sysUserContactMapper.deleteSysUserContactByUserIds(userIds);
    }

    /**
     * 删除用户和联系人关联信息
     * 
     * @param userId 用户和联系人关联主键
     * @return 结果
     */
    @Override
    public int deleteSysUserContactByUserId(Long userId)
    {
        return sysUserContactMapper.deleteSysUserContactByUserId(userId);
    }

    @Override
    public int deleteSysUserContactByUserIdAndContactId(SysUserContact sysUserContact) {
        return sysUserContactMapper.deleteSysUserContactByUserIdAndContactId(sysUserContact);
    }
}
