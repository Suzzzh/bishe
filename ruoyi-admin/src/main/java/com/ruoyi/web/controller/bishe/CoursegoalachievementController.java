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
import com.ruoyi.bishe.domain.Coursegoalachievement;
import com.ruoyi.bishe.service.ICoursegoalachievementService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 课程目标达成度表Controller
 * 
 * @author ruoyi
 * @date 2023-03-22
 */
@RestController
@RequestMapping("/bishe/coursegoalachievement")
public class CoursegoalachievementController extends BaseController
{
    @Autowired
    private ICoursegoalachievementService coursegoalachievementService;
    @Autowired
    private ICourseService courseService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysDeptService sysDeptService;
    /**
     * 查询课程目标达成度表列表
     */
    @PreAuthorize("@ss.hasPermi('bishe:coursegoalachievement:list')")
    @GetMapping("/list")
    public TableDataInfo list(Coursegoalachievement coursegoalachievement)
    {
        startPage();
        Long userId = getUserId();
        if (userId == 1L) {
            List<Coursegoalachievement> list = coursegoalachievementService.selectCoursegoalachievementList(coursegoalachievement);
            return getDataTable(list);
        }
        if (userId == 2 || userId == 100 || userId == 101 || userId == 102 || userId == 103 || userId == 104) {
            String major = sysDeptService.selectDeptNameById(getDeptId());
            List<Integer> courseIdList = courseService.selectCourseIdListByMajor(major);
            List<Coursegoalachievement> list = coursegoalachievementService.selectCoursegoalachievementByCourseIdList(courseIdList);
            return getDataTable(list);
        }
        List<Integer> courseIdList = courseService.selectCourseIdListByName(sysUserService.selectNickNameById(userId));
        List<Coursegoalachievement> list = coursegoalachievementService.selectCoursegoalachievementByCourseIdList(courseIdList);

        return getDataTable(list);
    }

    /**
     * 导出课程目标达成度表列表
     */
    @PreAuthorize("@ss.hasPermi('bishe:coursegoalachievement:export')")
    @Log(title = "课程目标达成度表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Coursegoalachievement coursegoalachievement)
    {
        List<Coursegoalachievement> list = coursegoalachievementService.selectCoursegoalachievementList(coursegoalachievement);
        ExcelUtil<Coursegoalachievement> util = new ExcelUtil<Coursegoalachievement>(Coursegoalachievement.class);
        util.exportExcel(response, list, "课程目标达成度表数据");
    }

    /**
     * 获取课程目标达成度表详细信息
     */
    @PreAuthorize("@ss.hasPermi('bishe:coursegoalachievement:query')")
    @GetMapping(value = "/{courseId}")
    public AjaxResult getInfo(@PathVariable("courseId") Integer courseId)
    {
        return success(coursegoalachievementService.selectCoursegoalachievementByCourseId(courseId));
    }

    /**
     * 新增课程目标达成度表
     */
    @PreAuthorize("@ss.hasPermi('bishe:coursegoalachievement:add')")
    @Log(title = "课程目标达成度表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Coursegoalachievement coursegoalachievement)
    {
        return toAjax(coursegoalachievementService.insertCoursegoalachievement(coursegoalachievement));
    }

    /**
     * 修改课程目标达成度表
     */
    @PreAuthorize("@ss.hasPermi('bishe:coursegoalachievement:edit')")
    @Log(title = "课程目标达成度表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Coursegoalachievement coursegoalachievement)
    {
        return toAjax(coursegoalachievementService.updateCoursegoalachievement(coursegoalachievement));
    }

    /**
     * 删除课程目标达成度表
     */
    @PreAuthorize("@ss.hasPermi('bishe:coursegoalachievement:remove')")
    @Log(title = "课程目标达成度表", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public AjaxResult remove(Integer courseId,String courseObjectives)
    {
        return toAjax(coursegoalachievementService.deleteCoursegoalachievementByCourseIdAndObj(courseId,courseObjectives));
    }
}
