package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 联系人详情信息对象 sys_contact_detail
 * 
 * @author ruoyi
 * @date 2024-11-24
 */
public class SysContactDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 详情编号 */
    @Excel(name = "编号")
    private Long sysContactDetailId;

    /** 联系人名称 */
    @Excel(name = "联系人名称")
    private String contactName;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phoneNumber;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 物理地址 */
    @Excel(name = "物理地址")
    private String address;

    /** 社交 */
    @Excel(name = "社交")
    private String social;

    public void setSysContactDetailId(Long sysContactDetailId) 
    {
        this.sysContactDetailId = sysContactDetailId;
    }

    public Long getSysContactDetailId() 
    {
        return sysContactDetailId;
    }
    public void setContactName(String contactName) 
    {
        this.contactName = contactName;
    }

    public String getContactName() 
    {
        return contactName;
    }
    public void setPhoneNumber(String phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() 
    {
        return phoneNumber;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setSocial(String social) 
    {
        this.social = social;
    }

    public String getSocial() 
    {
        return social;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sysContactDetailId", getSysContactDetailId())
            .append("contactName", getContactName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("phoneNumber", getPhoneNumber())
            .append("email", getEmail())
            .append("address", getAddress())
            .append("social", getSocial())
            .toString();
    }
}
