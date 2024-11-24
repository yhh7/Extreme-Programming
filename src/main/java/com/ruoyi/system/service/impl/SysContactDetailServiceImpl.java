package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanValidators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysContactDetailMapper;
import com.ruoyi.system.domain.SysContactDetail;
import com.ruoyi.system.service.ISysContactDetailService;

import javax.validation.Validator;

/**
 * 联系人详情信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-11-24
 */
@Service
public class SysContactDetailServiceImpl implements ISysContactDetailService 
{
    @Autowired
    private SysContactDetailMapper sysContactDetailMapper;

    private static final Logger log = LoggerFactory.getLogger(SysContactDetailServiceImpl.class);

    @Autowired
    protected Validator validator;


    /**
     * 查询联系人详情信息
     * 
     * @param sysContactDetailId 联系人详情信息主键
     * @return 联系人详情信息
     */
    @Override
    public SysContactDetail selectSysContactDetailBySysContactDetailId(Long sysContactDetailId)
    {
        return sysContactDetailMapper.selectSysContactDetailBySysContactDetailId(sysContactDetailId);
    }

    /**
     * 查询联系人详情信息列表
     * 
     * @param sysContactDetail 联系人详情信息
     * @return 联系人详情信息
     */
    @Override
    public List<SysContactDetail> selectSysContactDetailList(SysContactDetail sysContactDetail)
    {
        return sysContactDetailMapper.selectSysContactDetailList(sysContactDetail);
    }

    /**
     * 新增联系人详情信息
     * 
     * @param sysContactDetail 联系人详情信息
     * @return 结果
     */
    @Override
    public int insertSysContactDetail(SysContactDetail sysContactDetail)
    {
        sysContactDetail.setCreateTime(DateUtils.getNowDate());
        return sysContactDetailMapper.insertSysContactDetail(sysContactDetail);
    }

    /**
     * 修改联系人详情信息
     * 
     * @param sysContactDetail 联系人详情信息
     * @return 结果
     */
    @Override
    public int updateSysContactDetail(SysContactDetail sysContactDetail)
    {
        sysContactDetail.setUpdateTime(DateUtils.getNowDate());
        return sysContactDetailMapper.updateSysContactDetail(sysContactDetail);
    }

    /**
     * 批量删除联系人详情信息
     * 
     * @param sysContactDetailIds 需要删除的联系人详情信息主键
     * @return 结果
     */
    @Override
    public int deleteSysContactDetailBySysContactDetailIds(Long[] sysContactDetailIds)
    {
        return sysContactDetailMapper.deleteSysContactDetailBySysContactDetailIds(sysContactDetailIds);
    }

    /**
     * 删除联系人详情信息信息
     * 
     * @param sysContactDetailId 联系人详情信息主键
     * @return 结果
     */
    @Override
    public int deleteSysContactDetailBySysContactDetailId(Long sysContactDetailId)
    {
        return sysContactDetailMapper.deleteSysContactDetailBySysContactDetailId(sysContactDetailId);
    }

    @Override
    public String importSysContactDetailInfo(List<SysContactDetail> sysStudentInfoList, String operatorName) {
        if (StringUtils.isNull(sysStudentInfoList) || sysStudentInfoList.isEmpty()) {
            throw new ServiceException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (SysContactDetail item : sysStudentInfoList) {
            try {
                // 验证是否存在这个用户
                SysContactDetail u = sysContactDetailMapper.selectSysContactDetailBySysContactDetailId(item.getSysContactDetailId());
                if (StringUtils.isNull(u)) {
                    BeanValidators.validateWithException(validator, item);
                    item.setCreateBy(operatorName);
                    sysContactDetailMapper.insertSysContactDetail(item);
                    successNum++;
                    successMsg.append("<br/>").append(successNum).append("、编号 ").append(item.getSysContactDetailId()).append(" 导入成功");
                } else {
                    BeanValidators.validateWithException(validator, item);
                    item.setSysContactDetailId(u.getSysContactDetailId());
                    item.setUpdateBy(operatorName);

                    sysContactDetailMapper.updateSysContactDetail(item);
                    successNum++;
                    successMsg.append("<br/>").append(successNum).append("、编号 ").append(item.getSysContactDetailId()).append(" 更新成功");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、编号 " + item.getSysContactDetailId() + " 导入失败：";
                failureMsg.append(msg).append(e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}
