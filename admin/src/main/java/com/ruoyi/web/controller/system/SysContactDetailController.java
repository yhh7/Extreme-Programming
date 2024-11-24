package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.SysContact;
import com.ruoyi.system.service.ISysContactService;
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
import com.ruoyi.system.domain.SysContactDetail;
import com.ruoyi.system.service.ISysContactDetailService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 联系人详情信息Controller
 *
 * @author ruoyi
 * @date 2024-11-24
 */
@RestController
@RequestMapping("/system/detail")
public class SysContactDetailController extends BaseController {
    @Autowired
    private ISysContactDetailService sysContactDetailService;
    @Autowired
    private ISysContactService sysContactService;

    /**
     * 查询联系人详情信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:detail:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysContactDetail sysContactDetail) {
        startPage();
        List<SysContactDetail> list = sysContactDetailService.selectSysContactDetailList(sysContactDetail);
        return getDataTable(list);
    }

    /**
     * 导出联系人详情信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:detail:export')")
    @Log(title = "联系人详情信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysContactDetail sysContactDetail) {
        List<SysContactDetail> list = sysContactDetailService.selectSysContactDetailList(sysContactDetail);
        ExcelUtil<SysContactDetail> util = new ExcelUtil<SysContactDetail>(SysContactDetail.class);
        util.exportExcel(response, list, "联系人详情信息数据");
    }

    /**
     * 获取联系人详情信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:detail:query')")
    @GetMapping(value = "/{sysContactDetailId}")
    public AjaxResult getInfo(@PathVariable("sysContactDetailId") Long sysContactDetailId) {
        return success(sysContactDetailService.selectSysContactDetailBySysContactDetailId(sysContactDetailId));
    }

    /**
     * 新增联系人详情信息
     */
    @PreAuthorize("@ss.hasPermi('system:detail:add')")
    @Log(title = "联系人详情信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysContactDetail sysContactDetail) {
        return toAjax(sysContactDetailService.insertSysContactDetail(sysContactDetail));
    }

    /**
     * 修改联系人详情信息
     */
    @PreAuthorize("@ss.hasPermi('system:detail:edit')")
    @Log(title = "联系人详情信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysContactDetail sysContactDetail) {
        return toAjax(sysContactDetailService.updateSysContactDetail(sysContactDetail));
    }

    /**
     * 删除联系人详情信息
     */
    @PreAuthorize("@ss.hasPermi('system:detail:remove')")
    @Log(title = "联系人详情信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{sysContactDetailIds}")
    public AjaxResult remove(@PathVariable Long[] sysContactDetailIds) {
        return toAjax(sysContactDetailService.deleteSysContactDetailBySysContactDetailIds(sysContactDetailIds));
    }


    @GetMapping("/getContactList")
    public AjaxResult getContactList() {
        return success(sysContactService.selectSysContactList(new SysContact()));
    }

    @Log(title = "联系人信息导入", businessType = BusinessType.IMPORT)
    @PostMapping("/import")
    public AjaxResult importData(MultipartFile file) throws Exception {
        ExcelUtil<SysContactDetail> util = new ExcelUtil<>(SysContactDetail.class);
        List<SysContactDetail> sysArchiveReceiverList = util.importExcel(file.getInputStream());
        String operatorName = getUsername();
        String message = sysContactDetailService.importSysContactDetailInfo(sysArchiveReceiverList, operatorName);
        return success(message);
    }
}
