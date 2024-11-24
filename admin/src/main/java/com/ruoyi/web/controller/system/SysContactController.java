package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.SysUserContact;
import com.ruoyi.system.service.ISysUserContactService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysContact;
import com.ruoyi.system.service.ISysContactService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 联系人管理Controller
 *
 * @author ruoyi
 * @date 2024-11-24
 */
@RestController
@RequestMapping("/system/contact")
public class SysContactController extends BaseController {
    @Autowired
    private ISysContactService sysContactService;

    @Autowired
    private ISysUserContactService sysUserContactService;

    /**
     * 查询联系人管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:contact:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysContact sysContact) {
        startPage();
        List<SysContact> list = sysContactService.selectSysContactList(sysContact);
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUser().getUserId();
        List<SysUserContact> sysUserContacts = sysUserContactService.selectSysUserContactList(new SysUserContact());


        // 遍历联系人列表
        for (SysContact contact : list) {
            // 默认未收藏
            String isFavorite = "0";

            // 遍历用户联系人关联表
            for (SysUserContact userContact : sysUserContacts) {
                // 检查当前用户的userId和联系人的id是否匹配
                if (userContact.getUserId().equals(userId) && userContact.getContactId().equals(contact.getId())) {
                    isFavorite = "1";
                    break; // 找到匹配项后可以退出内层循环
                }
            }

            // 设置favoriteFlag
            contact.setFavoriteFlag(isFavorite);
        }
        return getDataTable(list);
    }

    /**
     * 导出联系人管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:contact:export')")
    @Log(title = "联系人管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysContact sysContact) {
        List<SysContact> list = sysContactService.selectSysContactList(sysContact);
        ExcelUtil<SysContact> util = new ExcelUtil<SysContact>(SysContact.class);
        util.exportExcel(response, list, "联系人管理数据");
    }

    /**
     * 获取联系人管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:contact:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(sysContactService.selectSysContactById(id));
    }

    /**
     * 新增联系人管理
     */
    @PreAuthorize("@ss.hasPermi('system:contact:add')")
    @Log(title = "联系人管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysContact sysContact) {
        return toAjax(sysContactService.insertSysContact(sysContact));
    }

    /**
     * 修改联系人管理
     */
    @PreAuthorize("@ss.hasPermi('system:contact:edit')")
    @Log(title = "联系人管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysContact sysContact) {
        return toAjax(sysContactService.updateSysContact(sysContact));
    }

    /**
     * 删除联系人管理
     */
    @PreAuthorize("@ss.hasPermi('system:contact:remove')")
    @Log(title = "联系人管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(sysContactService.deleteSysContactByIds(ids));
    }

    @PreAuthorize("@ss.hasPermi('system:contact:favorite')")
    @Log(title = "联系人管理", businessType = BusinessType.UPDATE)
    @PostMapping("/favorite/{id}")
    public AjaxResult favorite(@PathVariable Long id) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUser().getUserId();
        SysUserContact userContact = new SysUserContact();
        userContact.setUserId(userId);
        userContact.setContactId(id);
        return toAjax(sysUserContactService.insertSysUserContact(userContact));
    }

    @PreAuthorize("@ss.hasPermi('system:contact:favorite')")
    @Log(title = "联系人管理", businessType = BusinessType.UPDATE)
    @PostMapping("/unFavorite/{id}")
    public AjaxResult unFavorite(@PathVariable Long id) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUser().getUserId();
        SysUserContact userContact = new SysUserContact();
        userContact.setUserId(userId);
        userContact.setContactId(id);
        return toAjax(sysUserContactService.deleteSysUserContactByUserIdAndContactId(userContact));
    }
}
