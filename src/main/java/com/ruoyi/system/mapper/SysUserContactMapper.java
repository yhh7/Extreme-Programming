package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysUserContact;

/**
 * 用户和联系人关联Mapper接口
 * 
 * @author ruoyi
 * @date 2024-11-24
 */
public interface SysUserContactMapper 
{
    /**
     * 查询用户和联系人关联
     * 
     * @param userId 用户和联系人关联主键
     * @return 用户和联系人关联
     */
    public SysUserContact selectSysUserContactByUserId(Long userId);

    /**
     * 查询用户和联系人关联列表
     * 
     * @param sysUserContact 用户和联系人关联
     * @return 用户和联系人关联集合
     */
    public List<SysUserContact> selectSysUserContactList(SysUserContact sysUserContact);

    /**
     * 新增用户和联系人关联
     * 
     * @param sysUserContact 用户和联系人关联
     * @return 结果
     */
    public int insertSysUserContact(SysUserContact sysUserContact);

    /**
     * 修改用户和联系人关联
     * 
     * @param sysUserContact 用户和联系人关联
     * @return 结果
     */
    public int updateSysUserContact(SysUserContact sysUserContact);

    /**
     * 删除用户和联系人关联
     * 
     * @param userId 用户和联系人关联主键
     * @return 结果
     */
    public int deleteSysUserContactByUserId(Long userId);

    /**
     * 批量删除用户和联系人关联
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysUserContactByUserIds(Long[] userIds);

    public int deleteSysUserContactByUserIdAndContactId(SysUserContact sysUserContact);
}
