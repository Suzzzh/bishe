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
import com.ruoyi.bishe.domain.Excellent;
import com.ruoyi.bishe.service.IExcellentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 优秀学生名单Controller
 * 
 * @author suzhonghui
 * @date 2023-05-11
 */
@RestController
@RequestMapping("/bishe/excellent")
public class ExcellentController extends BaseController
{
    @Autowired
    private IExcellentService excellentService;
    @Autowired
    private ICourseService courseService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysDeptService sysDeptService;
    /**
     * 查询优秀学生名单列表
     */
    @PreAuthorize("@ss.hasPermi('bishe:excellent:list')")
    @GetMapping("/list")
    public TableDataInfo list(Excellent excellent)
    {
        startPage();
        Long userId = getUserId();
        if (userId == 1L) {
            List<Excellent> list = excellentService.selectExcellentList(excellent);
            return getDataTable(list);
        }
        if (userId == 2 || userId == 100 || userId == 101 || userId == 102 || userId == 103 || userId == 104) {
            String major = sysDeptService.selectDeptNameById(getDeptId());
            List<Integer> courseIdList = courseService.selectCourseIdListByMajor(major);
            List<Excellent> list = excellentService.selectEvaluationpahseByCourseIdList(courseIdList);
            return getDataTable(list);
        }
        List<Integer> courseIdList = courseService.selectCourseIdListByName(sysUserService.selectNickNameById(userId));
        List<Excellent> list = excellentService.selectEvaluationpahseByCourseIdList(courseIdList);


        return getDataTable(list);
    }

    /**
     * 导出优秀学生名单列表
     */
    @PreAuthorize("@ss.hasPermi('bishe:excellent:export')")
    @Log(title = "优秀学生名单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Excellent excellent)
    {
        List<Excellent> list = excellentService.selectExcellentList(excellent);
        ExcelUtil<Excellent> util = new ExcelUtil<Excellent>(Excellent.class);
        util.exportExcel(response, list, "优秀学生名单数据");
    }

    /**
     * 获取优秀学生名单详细信息
     */
    @PreAuthorize("@ss.hasPermi('bishe:excellent:query')")
    @GetMapping(value = "/{courseId}")
    public AjaxResult getInfo(@PathVariable("courseId") Integer courseId)
    {
        return success(excellentService.selectExcellentByCourseId(courseId));
    }

    /**
     * 新增优秀学生名单
     */
    @PreAuthorize("@ss.hasPermi('bishe:excellent:add')")
    @Log(title = "优秀学生名单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Excellent excellent)
    {
        return toAjax(excellentService.insertExcellent(excellent));
    }

    /**
     * 修改优秀学生名单
     */
    @PreAuthorize("@ss.hasPermi('bishe:excellent:edit')")
    @Log(title = "优秀学生名单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Excellent excellent)
    {
        return toAjax(excellentService.updateExcellent(excellent));
    }

    /**
     * 删除优秀学生名单
     */
    @PreAuthorize("@ss.hasPermi('bishe:excellent:remove')")
    @Log(title = "优秀学生名单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{courseIds}")
    public AjaxResult remove(@PathVariable Integer[] courseIds)
    {
        return toAjax(excellentService.deleteExcellentByCourseIds(courseIds));
    }
}
