package com.ruoyi.web.controller.bishe;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.bishe.domain.Auxiliarycalculation;
import com.ruoyi.bishe.domain.Document;
import com.ruoyi.bishe.domain.Zhiliang;
import com.ruoyi.bishe.service.IAuxiliarycalculationService;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysUserService;
import fr.opensagres.xdocreport.core.XDocReportException;
import io.swagger.models.auth.In;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.bishe.domain.Course;
import com.ruoyi.bishe.service.ICourseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 总课程表Controller
 *
 * @author suzhonghui
 * @date 2023-03-19
 */
@RestController
@RequestMapping("/bishe/course")
public class CourseController extends BaseController {
    @Autowired
    private ICourseService courseService;

    @Autowired
    private IAuxiliarycalculationService auxiliarycalculationService;
    @Autowired
    private ISysDeptService sysDeptService;
    @Autowired
    private ISysUserService sysUserService;
    /**
     * 查询总课程表列表
     */
    @PreAuthorize("@ss.hasPermi('bishe:course:list')")
    @GetMapping("/list")
    public TableDataInfo list(Course course) {
        startPage();
        Long userId = getUserId();
        if (userId==1L){
            List<Course> list = courseService.selectCourseList(course);
            return getDataTable(list);
        }
        if (userId == 2 ||userId == 100|| userId == 101 || userId == 102 || userId == 103 || userId == 104){
            Long deptId = getDeptId();
            course.setMajor(sysDeptService.selectDeptNameById(deptId));
            List<Course> list = courseService.selectCourseList(course);
            return getDataTable(list);
        }
        if(userId!=1L){
            course.setTeacherName(sysUserService.selectNickNameById(userId));
            List<Course> list = courseService.selectCourseList(course);
            return getDataTable(list);
        }
        List<Course> list = courseService.selectCourseList(course);
        return getDataTable(list);
    }

    /**
     * 导出总课程表列表
     */
    @PreAuthorize("@ss.hasPermi('bishe:course:export')")
    @Log(title = "总课程表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Course course) {
        List<Course> list = courseService.selectCourseList(course);
        ExcelUtil<Course> util = new ExcelUtil<Course>(Course.class);
        util.exportExcel(response, list, "总课程表数据");
    }

    /**
     * 获取总课程表详细信息
     */
    @PreAuthorize("@ss.hasPermi('bishe:course:query')")
    @GetMapping(value = "/{courseId}")
    public AjaxResult getInfo(@PathVariable("courseId") Integer courseId) {

        return success(courseService.selectCourseByCourseId(courseId));
    }

    /**
     * 新增总课程表
     */
    @PreAuthorize("@ss.hasPermi('bishe:course:add')")
    @Log(title = "总课程表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Course course) {
        Integer courseId = course.getCourseId();
        Auxiliarycalculation auxiliarycalculation = new Auxiliarycalculation();
        auxiliarycalculation.setCourseId(courseId);
        auxiliarycalculationService.insertAuxiliarycalculation(auxiliarycalculation);
        return toAjax(courseService.insertCourse(course));
    }

    /**
     * 修改总课程表
     */
    @PreAuthorize("@ss.hasPermi('bishe:course:edit')")
    @Log(title = "总课程表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Course course) {
        return toAjax(courseService.updateCourse(course));
    }

    /**
     * 删除总课程表
     */
    @PreAuthorize("@ss.hasPermi('bishe:course:remove')")
    @Log(title = "总课程表", businessType = BusinessType.DELETE)
    @DeleteMapping("/{courseIds}")
    public AjaxResult remove(@PathVariable Integer[] courseIds) {
        auxiliarycalculationService.deleteAuxiliarycalculationByCourseIds(courseIds);
        return toAjax(courseService.deleteCourseByCourseIds(courseIds));
    }

    /**
     * 导出质量报告
     * @param
     * @return
     */
    @PreAuthorize("@ss.hasPermi('bishe:course:zhiliang')")
//    @GetMapping ("/zhiliang/{courseId}")
    @PostMapping("/zhiliang")
    public void zhiliang (Zhiliang zhiliang,HttpServletResponse response) throws IOException, XDocReportException {
        courseService.downloadZhiliang(response,zhiliang);
    }
}
