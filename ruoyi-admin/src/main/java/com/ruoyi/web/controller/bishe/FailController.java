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
import com.ruoyi.bishe.domain.Fail;
import com.ruoyi.bishe.service.IFailService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 不及格学生名单Controller
 * 
 * @author suzhonghui
 * @date 2023-05-11
 */
@RestController
@RequestMapping("/bishe/fail")
public class FailController extends BaseController
{
    @Autowired
    private IFailService failService;
    @Autowired
    private ICourseService courseService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysDeptService sysDeptService;

    /**
     * 查询不及格学生名单列表
     */
    @PreAuthorize("@ss.hasPermi('bishe:fail:list')")
    @GetMapping("/list")
    public TableDataInfo list(Fail fail)
    {
        startPage();

        Long userId = getUserId();
        if (userId == 1L) {
            List<Fail> list = failService.selectFailList(fail);
            return getDataTable(list);
        }
        if (userId == 2 || userId == 100 || userId == 101 || userId == 102 || userId == 103 || userId == 104) {
            String major = sysDeptService.selectDeptNameById(getDeptId());
            List<Integer> courseIdList = courseService.selectCourseIdListByMajor(major);
            List<Fail> list = failService.selectEvaluationpahseByCourseIdList(courseIdList);
            return getDataTable(list);
        }
        List<Integer> courseIdList = courseService.selectCourseIdListByName(sysUserService.selectNickNameById(userId));
        List<Fail> list = failService.selectEvaluationpahseByCourseIdList(courseIdList);

        return getDataTable(list);
    }

    /**
     * 导出不及格学生名单列表
     */
    @PreAuthorize("@ss.hasPermi('bishe:fail:export')")
    @Log(title = "不及格学生名单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Fail fail)
    {
        List<Fail> list = failService.selectFailList(fail);
        ExcelUtil<Fail> util = new ExcelUtil<Fail>(Fail.class);
        util.exportExcel(response, list, "不及格学生名单数据");
    }

    /**
     * 获取不及格学生名单详细信息
     */
    @PreAuthorize("@ss.hasPermi('bishe:fail:query')")
    @GetMapping(value = "/{courseId}")
    public AjaxResult getInfo(@PathVariable("courseId") Integer courseId)
    {
        return success(failService.selectFailByCourseId(courseId));
    }

    /**
     * 新增不及格学生名单
     */
    @PreAuthorize("@ss.hasPermi('bishe:fail:add')")
    @Log(title = "不及格学生名单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Fail fail)
    {
        return toAjax(failService.insertFail(fail));
    }

    /**
     * 修改不及格学生名单
     */
    @PreAuthorize("@ss.hasPermi('bishe:fail:edit')")
    @Log(title = "不及格学生名单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Fail fail)
    {
        return toAjax(failService.updateFail(fail));
    }

    /**
     * 删除不及格学生名单
     */
    @PreAuthorize("@ss.hasPermi('bishe:fail:remove')")
    @Log(title = "不及格学生名单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{courseIds}")
    public AjaxResult remove(@PathVariable Integer[] courseIds)
    {
        return toAjax(failService.deleteFailByCourseIds(courseIds));
    }
}
