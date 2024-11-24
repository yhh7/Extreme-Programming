package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysContact;

/**
 * 联系人管理Mapper接口
 * 
 * @author ruoyi
 * @date 2024-11-24
 */
public interface SysContactMapper 
{
    /**
     * 查询联系人管理
     * 
     * @param id 联系人管理主键
     * @return 联系人管理
     */
    public SysContact selectSysContactById(Long id);

    /**
     * 查询联系人管理列表
     * 
     * @param sysContact 联系人管理
     * @return 联系人管理集合
     */
    public List<SysContact> selectSysContactList(SysContact sysContact);

    /**
     * 新增联系人管理
     * 
     * @param sysContact 联系人管理
     * @return 结果
     */
    public int insertSysContact(SysContact sysContact);

    /**
     * 修改联系人管理
     * 
     * @param sysContact 联系人管理
     * @return 结果
     */
    public int updateSysContact(SysContact sysContact);

    /**
     * 删除联系人管理
     * 
     * @param id 联系人管理主键
     * @return 结果
     */
    public int deleteSysContactById(Long id);

    /**
     * 批量删除联系人管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysContactByIds(Long[] ids);
}
