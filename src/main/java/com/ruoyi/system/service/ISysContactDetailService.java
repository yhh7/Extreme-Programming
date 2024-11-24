package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysContactDetail;

/**
 * 联系人详情信息Service接口
 * 
 * @author ruoyi
 * @date 2024-11-24
 */
public interface ISysContactDetailService 
{
    /**
     * 查询联系人详情信息
     * 
     * @param sysContactDetailId 联系人详情信息主键
     * @return 联系人详情信息
     */
    public SysContactDetail selectSysContactDetailBySysContactDetailId(Long sysContactDetailId);

    /**
     * 查询联系人详情信息列表
     * 
     * @param sysContactDetail 联系人详情信息
     * @return 联系人详情信息集合
     */
    public List<SysContactDetail> selectSysContactDetailList(SysContactDetail sysContactDetail);

    /**
     * 新增联系人详情信息
     * 
     * @param sysContactDetail 联系人详情信息
     * @return 结果
     */
    public int insertSysContactDetail(SysContactDetail sysContactDetail);

    /**
     * 修改联系人详情信息
     * 
     * @param sysContactDetail 联系人详情信息
     * @return 结果
     */
    public int updateSysContactDetail(SysContactDetail sysContactDetail);

    /**
     * 批量删除联系人详情信息
     * 
     * @param sysContactDetailIds 需要删除的联系人详情信息主键集合
     * @return 结果
     */
    public int deleteSysContactDetailBySysContactDetailIds(Long[] sysContactDetailIds);

    /**
     * 删除联系人详情信息信息
     * 
     * @param sysContactDetailId 联系人详情信息主键
     * @return 结果
     */
    public int deleteSysContactDetailBySysContactDetailId(Long sysContactDetailId);

    public String importSysContactDetailInfo(List<SysContactDetail> sysStudentInfoList, String operatorName);
}
