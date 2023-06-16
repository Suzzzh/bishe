package com.ruoyi.web.controller.bishe;

import java.math.BigDecimal;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.bishe.domain.Document;
import com.ruoyi.bishe.service.ICourseService;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysUserService;
import io.swagger.models.auth.In;
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
import com.ruoyi.bishe.domain.Evaluationpahse;
import com.ruoyi.bishe.service.IEvaluationpahseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 评价环节分配Controller
 *
 * @author suzhonghui
 * @date 2023-03-22
 */
@RestController
@RequestMapping("/bishe/evaluationpahse")
public class EvaluationpahseController extends BaseController {
    @Autowired
    private IEvaluationpahseService evaluationpahseService;
    @Autowired
    private ICourseService courseService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysDeptService sysDeptService;

    /**
     * 查询评价环节分配列表
     */
    @PreAuthorize("@ss.hasPermi('bishe:evaluationpahse:list')")
    @GetMapping("/list")
    public TableDataInfo list(Evaluationpahse evaluationpahse) {
        startPage();
        Long userId = getUserId();
        if (userId == 1L) {
            List<Evaluationpahse> list = evaluationpahseService.selectEvaluationpahseList(evaluationpahse);
            return getDataTable(list);
        }
        if (userId == 2 || userId == 100 || userId == 101 || userId == 102 || userId == 103 || userId == 104) {
            String major = sysDeptService.selectDeptNameById(getDeptId());
            List<Integer> courseIdList = courseService.selectCourseIdListByMajor(major);
            List<Evaluationpahse> list = evaluationpahseService.selectEvaluationpahseByCourseIdList(courseIdList);
            return getDataTable(list);
        }
        List<Integer> courseIdList = courseService.selectCourseIdListByName(sysUserService.selectNickNameById(userId));
        List<Evaluationpahse> evaluationpahses = evaluationpahseService.selectEvaluationpahseByCourseIdList(courseIdList);

        return getDataTable(evaluationpahses);
    }

    /**
     * 导出评价环节分配列表
     */
    @PreAuthorize("@ss.hasPermi('bishe:evaluationpahse:export')")
    @Log(title = "评价环节分配", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Evaluationpahse evaluationpahse) {
        List<Evaluationpahse> list = evaluationpahseService.selectEvaluationpahseList(evaluationpahse);
        ExcelUtil<Evaluationpahse> util = new ExcelUtil<Evaluationpahse>(Evaluationpahse.class);
        util.exportExcel(response, list, "评价环节分配数据");
    }

    /**
     * 获取评价环节分配详细信息
     */
    @PreAuthorize("@ss.hasPermi('bishe:evaluationpahse:query')")
    @GetMapping(value = "/{courseId}")
    public AjaxResult getInfo(@PathVariable("courseId") Integer courseId) {
        return success(evaluationpahseService.selectEvaluationpahseByCourseId(courseId));
    }

    /**
     * 新增评价环节分配
     */
    @PreAuthorize("@ss.hasPermi('bishe:evaluationpahse:add')")
    @Log(title = "评价环节分配", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Evaluationpahse evaluationpahse) {
        int sum = 0;
        if (evaluationpahse.getMidtermSco() != null)
            sum += evaluationpahse.getMidtermSco().intValue();
        if (evaluationpahse.getHomeworkSco() != null)
            sum += evaluationpahse.getHomeworkSco().intValue();
        if (evaluationpahse.getQuizSco() != null)
            sum += evaluationpahse.getQuizSco().intValue();
        if (evaluationpahse.getLargeSco() != null)
            sum += evaluationpahse.getLargeSco().intValue();
        if (evaluationpahse.getExperimentSco() != null)
            sum += evaluationpahse.getExperimentSco().intValue();
        if (evaluationpahse.getReplySco() != null)
            sum += evaluationpahse.getReplySco().intValue();
        if (evaluationpahse.getThesisSco() != null)
            sum += evaluationpahse.getThesisSco().intValue();
        if (evaluationpahse.getFinalSco() != null)
            sum += evaluationpahse.getFinalSco().intValue();

        if (sum == 100) {
            evaluationpahse.setRatioSum(BigDecimal.valueOf(sum));
        } else if (sum > 100) {
            return error("比例合计大于100，请重新分配各环节分数比例,当前比例和为:" + sum);
        } else if (sum < 100) {
            return error("比例合计小于100，请重新分配各环节分数比例,当前比例和为:" + sum);
        }
        return toAjax(evaluationpahseService.insertEvaluationpahse(evaluationpahse));
    }

    /**
     * 修改评价环节分配
     */
    @PreAuthorize("@ss.hasPermi('bishe:evaluationpahse:edit')")
    @Log(title = "评价环节分配", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Evaluationpahse evaluationpahse) {
        int sum = 0;
        if (evaluationpahse.getMidtermSco() == null)
            evaluationpahse.setMidtermSco(BigDecimal.valueOf(0));
        if (evaluationpahse.getHomeworkSco() == null)
            evaluationpahse.setHomeworkSco(BigDecimal.valueOf(0));
        if (evaluationpahse.getQuizSco() == null)
            evaluationpahse.setQuizSco(BigDecimal.valueOf(0));
        if (evaluationpahse.getLargeSco() == null)
            evaluationpahse.setLargeSco(BigDecimal.valueOf(0));
        if (evaluationpahse.getExperimentSco() == null)
            evaluationpahse.setExperimentSco(BigDecimal.valueOf(0));
        if (evaluationpahse.getReplySco() == null)
            evaluationpahse.setReplySco(BigDecimal.valueOf(0));
        if (evaluationpahse.getThesisSco() == null)
            evaluationpahse.setThesisSco(BigDecimal.valueOf(0));
        if (evaluationpahse.getFinalSco() == null)
            evaluationpahse.setFinalSco(BigDecimal.valueOf(0));


        if (evaluationpahse.getMidtermSco() != null)
            sum += evaluationpahse.getMidtermSco().intValue();
        if (evaluationpahse.getHomeworkSco() != null)
            sum += evaluationpahse.getHomeworkSco().intValue();
        if (evaluationpahse.getQuizSco() != null)
            sum += evaluationpahse.getQuizSco().intValue();
        if (evaluationpahse.getLargeSco() != null)
            sum += evaluationpahse.getLargeSco().intValue();
        if (evaluationpahse.getExperimentSco() != null)
            sum += evaluationpahse.getExperimentSco().intValue();
        if (evaluationpahse.getReplySco() != null)
            sum += evaluationpahse.getReplySco().intValue();
        if (evaluationpahse.getThesisSco() != null)
            sum += evaluationpahse.getThesisSco().intValue();
        if (evaluationpahse.getFinalSco() != null)
            sum += evaluationpahse.getFinalSco().intValue();

        if (sum == 100) {
            evaluationpahse.setRatioSum(BigDecimal.valueOf(sum));
        } else if (sum > 100) {
            return error("比例合计大于100，请重新分配各环节分数比例，当前比例和为:" + sum);
        } else if (sum < 100) {
            return error("比例合计小于100，请重新分配各环节分数比例，当前比例和为" + sum);
        }
        return toAjax(evaluationpahseService.updateEvaluationpahse(evaluationpahse));
    }

    /**
     * 删除评价环节分配
     */
    @PreAuthorize("@ss.hasPermi('bishe:evaluationpahse:remove')")
    @Log(title = "评价环节分配", businessType = BusinessType.DELETE)
    @DeleteMapping("/{courseIds}")
    public AjaxResult remove(@PathVariable Integer[] courseIds) {
        return toAjax(evaluationpahseService.deleteEvaluationpahseByCourseIds(courseIds));
    }
}
