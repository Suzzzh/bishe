package com.ruoyi.web.controller.bishe;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.bishe.domain.Course;
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
import com.ruoyi.bishe.domain.Achievedata;
import com.ruoyi.bishe.service.IAchievedataService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 生成达成数据Controller
 * 
 * @author suzhonghui
 * @date 2023-04-03
 */
@RestController
@RequestMapping("/bishe/achievedata")
public class AchievedataController extends BaseController
{
    @Autowired
    private IAchievedataService achievedataService;
    @Autowired
    private ICourseService courseService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysDeptService sysDeptService;
    /**
     * 查询生成达成数据列表
     */
    @PreAuthorize("@ss.hasPermi('bishe:achievedata:list')")
    @GetMapping("/list")
    public TableDataInfo list(Achievedata achievedata)
    {
        startPage();
        Long userId = getUserId();
        if (userId==1L){
            List<Achievedata> list = achievedataService.selectAchievedataList(achievedata);
            return getDataTable(list);
        }
        if (userId == 2 || userId == 100 || userId == 101 || userId == 102 || userId == 103 || userId == 104) {
            String major = sysDeptService.selectDeptNameById(getDeptId());
            List<Integer> courseIdList = courseService.selectCourseIdListByMajor(major);
            List<Achievedata> list = achievedataService.selectAchievedataByCourseIdList(courseIdList);
            return getDataTable(list);
        }
        List<Integer> courseIdList = courseService.selectCourseIdListByName(sysUserService.selectNickNameById(userId));
        List<Achievedata> list = achievedataService.selectAchievedataByCourseIdList(courseIdList);

        return getDataTable(list);
    }

    /**
     * 导出生成达成数据列表
     */
    @PreAuthorize("@ss.hasPermi('bishe:achievedata:export')")
    @Log(title = "生成达成数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Achievedata achievedata)
    {
        List<Achievedata> list = achievedataService.selectAchievedataList(achievedata);
        ExcelUtil<Achievedata> util = new ExcelUtil<Achievedata>(Achievedata.class);
        util.exportExcel(response, list, "生成达成数据数据");
    }

    /**
     * 获取生成达成数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('bishe:achievedata:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(achievedataService.selectAchievedataById(id));
    }

    /**
     * 新增生成达成数据
     */
    @PreAuthorize("@ss.hasPermi('bishe:achievedata:add')")
    @Log(title = "生成达成数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Achievedata achievedata)
    {
        //测试git
        return toAjax(achievedataService.insertAchievedata(achievedata));
    }

    /**
     * 修改生成达成数据
     */
    @PreAuthorize("@ss.hasPermi('bishe:achievedata:edit')")
    @Log(title = "生成达成数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Achievedata achievedata)
    {
        return toAjax(achievedataService.updateAchievedata(achievedata));
    }

    /**
     * 删除生成达成数据
     */
    @PreAuthorize("@ss.hasPermi('bishe:achievedata:remove')")
    @Log(title = "生成达成数据", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public AjaxResult remove(Integer id,String num)
    {
        return toAjax(achievedataService.deleteAchievedataByIdAndNum(id,num));
    }
}
