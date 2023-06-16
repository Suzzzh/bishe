package com.ruoyi.web.controller.bishe;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.bishe.domain.Evaluationpahse;
import com.ruoyi.bishe.service.ICourseService;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysUserService;
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
import com.ruoyi.bishe.domain.Auxiliarycalculation;
import com.ruoyi.bishe.service.IAuxiliarycalculationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 辅助计算表Controller
 * 
 * @author suzhonghui
 * @date 2023-03-22
 */
@RestController
@RequestMapping("/bishe/auxiliarycalculation")
public class AuxiliarycalculationController extends BaseController
{
    @Autowired
    private IAuxiliarycalculationService auxiliarycalculationService;
    @Autowired
    private ICourseService courseService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysDeptService sysDeptService;
    /**
     * 查询辅助计算表列表
     */
    @PreAuthorize("@ss.hasPermi('bishe:auxiliarycalculation:list')")
    @GetMapping("/list")
    public TableDataInfo list(Auxiliarycalculation auxiliarycalculation)
    {
        startPage();

        Long userId = getUserId();
        if (userId == 1L) {
            List<Auxiliarycalculation> list = auxiliarycalculationService.selectAuxiliarycalculationList(auxiliarycalculation);
            return getDataTable(list);
        }
        if (userId == 2 || userId == 100 || userId == 101 || userId == 102 || userId == 103 || userId == 104) {
            String major = sysDeptService.selectDeptNameById(getDeptId());
            List<Integer> courseIdList = courseService.selectCourseIdListByMajor(major);
            List<Auxiliarycalculation> list = auxiliarycalculationService.selectAuxiliarycalculationByCourseIdList(courseIdList);
            return getDataTable(list);
        }
        List<Integer> courseIdList = courseService.selectCourseIdListByName(sysUserService.selectNickNameById(userId));
        List<Auxiliarycalculation> list = auxiliarycalculationService.selectAuxiliarycalculationByCourseIdList(courseIdList);

        return getDataTable(list);
    }

    /**
     * 导出辅助计算表列表
     */
    @PreAuthorize("@ss.hasPermi('bishe:auxiliarycalculation:export')")
    @Log(title = "辅助计算表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Auxiliarycalculation auxiliarycalculation)
    {
        List<Auxiliarycalculation> list = auxiliarycalculationService.selectAuxiliarycalculationList(auxiliarycalculation);
        ExcelUtil<Auxiliarycalculation> util = new ExcelUtil<Auxiliarycalculation>(Auxiliarycalculation.class);
        util.exportExcel(response, list, "辅助计算表数据");
    }

    /**
     * 获取辅助计算表详细信息
     */
    @PreAuthorize("@ss.hasPermi('bishe:auxiliarycalculation:query')")
    @GetMapping(value = "/{courseId}")
    public AjaxResult getInfo(@PathVariable("courseId") Integer courseId)
    {
        return success(auxiliarycalculationService.selectAuxiliarycalculationByCourseId(courseId));
    }

    /**
     * 新增辅助计算表
     */
    @PreAuthorize("@ss.hasPermi('bishe:auxiliarycalculation:add')")
    @Log(title = "辅助计算表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Auxiliarycalculation auxiliarycalculation)
    {
        return toAjax(auxiliarycalculationService.insertAuxiliarycalculation(auxiliarycalculation));
    }

    /**
     * 修改辅助计算表
     */
    @PreAuthorize("@ss.hasPermi('bishe:auxiliarycalculation:edit')")
    @Log(title = "辅助计算表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Auxiliarycalculation auxiliarycalculation)
    {
        return toAjax(auxiliarycalculationService.updateAuxiliarycalculation(auxiliarycalculation));
    }

    /**
     * 删除辅助计算表
     */
    @PreAuthorize("@ss.hasPermi('bishe:auxiliarycalculation:remove')")
    @Log(title = "辅助计算表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{courseIds}")
    public AjaxResult remove(@PathVariable Integer[] courseIds)
    {
        return toAjax(auxiliarycalculationService.deleteAuxiliarycalculationByCourseIds(courseIds));
    }
}
