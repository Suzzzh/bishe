package com.ruoyi.web.controller.bishe;

import java.math.BigDecimal;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import ch.qos.logback.core.pattern.ConverterUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.bishe.domain.Auxiliarycalculation;
import com.ruoyi.bishe.domain.Evaluationpahse;
import com.ruoyi.bishe.service.IAuxiliarycalculationService;
import com.ruoyi.bishe.service.ICourseService;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysUserService;
import io.swagger.models.auth.In;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.bishe.domain.Finalexamscoredistribution;
import com.ruoyi.bishe.service.IFinalexamscoredistributionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 期末考试各题支撑目标分数分配Controller
 *
 * @author suzhonghui
 * @date 2023-03-22
 */
@RestController
@RequestMapping("/bishe/finalexamscoredistribution")
public class FinalexamscoredistributionController extends BaseController {
    @Autowired
    private IFinalexamscoredistributionService finalexamscoredistributionService;

    @Autowired
    private IAuxiliarycalculationService auxiliarycalculationService;

    @Autowired
    private ICourseService courseService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysDeptService sysDeptService;

    /**
     * 查询期末考试各题支撑目标分数分配列表
     */
    @PreAuthorize("@ss.hasPermi('bishe:finalexamscoredistribution:list')")
    @GetMapping("/list")
    public TableDataInfo list(Finalexamscoredistribution finalexamscoredistribution) {
        startPage();
        Long userId = getUserId();
        if (userId == 1L) {
            List<Finalexamscoredistribution> list = finalexamscoredistributionService.selectFinalexamscoredistributionList(finalexamscoredistribution);
            return getDataTable(list);
        }
        if (userId == 2 || userId == 100 || userId == 101 || userId == 102 || userId == 103 || userId == 104) {
            String major = sysDeptService.selectDeptNameById(getDeptId());
            List<Integer> courseIdList = courseService.selectCourseIdListByMajor(major);
            List<Finalexamscoredistribution> list = finalexamscoredistributionService.selectFinalexamscoredistributionByCourseIdList(courseIdList);
            return getDataTable(list);
        }
        List<Integer> courseIdList = courseService.selectCourseIdListByName(sysUserService.selectNickNameById(userId));
        List<Finalexamscoredistribution> finalexamscoredistributionList = finalexamscoredistributionService.selectFinalexamscoredistributionByCourseIdList(courseIdList);
        return getDataTable(finalexamscoredistributionList);
    }

    /**
     * 导出期末考试各题支撑目标分数分配列表
     */
    @PreAuthorize("@ss.hasPermi('bishe:finalexamscoredistribution:export')")
    @Log(title = "期末考试各题支撑目标分数分配", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Finalexamscoredistribution finalexamscoredistribution) {
        List<Finalexamscoredistribution> list = finalexamscoredistributionService.selectFinalexamscoredistributionList(finalexamscoredistribution);
        ExcelUtil<Finalexamscoredistribution> util = new ExcelUtil<Finalexamscoredistribution>(Finalexamscoredistribution.class);
        util.exportExcel(response, list, "期末考试各题支撑目标分数分配数据");
    }

    /**
     * 获取期末考试各题支撑目标分数分配详细信息
     */
    @PreAuthorize("@ss.hasPermi('bishe:finalexamscoredistribution:query')")
    @PostMapping("/find")
    public AjaxResult getInfo(Integer courseId, String courseObjectives) {
        return success(finalexamscoredistributionService.selectFinalexamscoredistributionByCourseId(courseId, courseObjectives));
    }

    /**
     * 新增期末考试各题支撑目标分数分配
     */
    @PreAuthorize("@ss.hasPermi('bishe:finalexamscoredistribution:add')")
    @Log(title = "期末考试各题支撑目标分数分配", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Finalexamscoredistribution finalexamscoredistribution) {
        Integer courseId = finalexamscoredistribution.getCourseId();
        int sum = 0;
        if (finalexamscoredistribution.getFirQue() != null)
            sum += finalexamscoredistribution.getFirQue().intValue();
        if (finalexamscoredistribution.getSecQue() != null)
            sum += finalexamscoredistribution.getSecQue().intValue();
        if (finalexamscoredistribution.getThiQue() != null)
            sum += finalexamscoredistribution.getThiQue().intValue();
        if (finalexamscoredistribution.getFouQue() != null)
            sum += finalexamscoredistribution.getFouQue().intValue();
        if (finalexamscoredistribution.getFifQue() != null)
            sum += finalexamscoredistribution.getFifQue().intValue();
        if (finalexamscoredistribution.getSixQue() != null)
            sum += finalexamscoredistribution.getSixQue().intValue();
        if (finalexamscoredistribution.getSevQue() != null)
            sum += finalexamscoredistribution.getSevQue().intValue();
        if (finalexamscoredistribution.getEigQue() != null)
            sum += finalexamscoredistribution.getEigQue().intValue();
        if (finalexamscoredistribution.getNinQue() != null)
            sum += finalexamscoredistribution.getNinQue().intValue();
        if (finalexamscoredistribution.getTenQue() != null)
            sum += finalexamscoredistribution.getTenQue().intValue();
        finalexamscoredistribution.setMaxSco(BigDecimal.valueOf(sum));
        int i = finalexamscoredistributionService.insertFinalexamscoredistribution(finalexamscoredistribution);

        BigDecimal firTotal = finalexamscoredistributionService.countFirQue(courseId);
        BigDecimal secTotal = finalexamscoredistributionService.countSecQue(courseId);
        BigDecimal thiTotal = finalexamscoredistributionService.countThiQue(courseId);
        BigDecimal fouTotal = finalexamscoredistributionService.countFouQue(courseId);
        BigDecimal fifTotal = finalexamscoredistributionService.countFifQue(courseId);
        BigDecimal sixTotal = finalexamscoredistributionService.countSixQue(courseId);
        BigDecimal sevTotal = finalexamscoredistributionService.countSevQue(courseId);
        BigDecimal eigTotal = finalexamscoredistributionService.countEigQue(courseId);
        BigDecimal ninTotal = finalexamscoredistributionService.countNinQue(courseId);
        BigDecimal tenTotal = finalexamscoredistributionService.countTenQue(courseId);
        Auxiliarycalculation auxiliarycalculation = new Auxiliarycalculation();
        auxiliarycalculation.setCourseId(courseId);
        if (firTotal == null) {
            auxiliarycalculation.setFirMax(BigDecimal.valueOf(0));
        } else {
            auxiliarycalculation.setFirMax(firTotal);
        }
        if (secTotal == null) {
            auxiliarycalculation.setSecMax(BigDecimal.valueOf(0));
        } else {
            auxiliarycalculation.setSecMax(secTotal);
        }
        if (thiTotal == null) {
            auxiliarycalculation.setThiMax(BigDecimal.valueOf(0));
        } else {
            auxiliarycalculation.setThiMax(thiTotal);
        }
        if (fouTotal == null) {
            auxiliarycalculation.setFouMax(BigDecimal.valueOf(0));
        } else {
            auxiliarycalculation.setFouMax(fouTotal);
        }
        if (fifTotal == null) {
            auxiliarycalculation.setFifMax(BigDecimal.valueOf(0));
        } else {
            auxiliarycalculation.setFifMax(fifTotal);
        }
        if (sixTotal == null) {
            auxiliarycalculation.setSixMax(BigDecimal.valueOf(0));
        } else {
            auxiliarycalculation.setSixMax(sixTotal);
        }
        if (sevTotal == null) {
            auxiliarycalculation.setSevMax(BigDecimal.valueOf(0));
        } else {
            auxiliarycalculation.setSevMax(sevTotal);
        }
        if (eigTotal == null) {
            auxiliarycalculation.setEigMax(BigDecimal.valueOf(0));
        } else {
            auxiliarycalculation.setEigMax(eigTotal);
        }
        if (ninTotal == null) {
            auxiliarycalculation.setNinMax(BigDecimal.valueOf(0));
        } else {
            auxiliarycalculation.setNinMax(ninTotal);
        }
        if (tenTotal == null) {
            auxiliarycalculation.setTenMax(BigDecimal.valueOf(0));
        } else {
            auxiliarycalculation.setTenMax(tenTotal);
        }
        auxiliarycalculationService.updateAuxiliarycalculation(auxiliarycalculation);

        return toAjax(i);
    }

    /**
     * 修改期末考试各题支撑目标分数分配
     */
    @PreAuthorize("@ss.hasPermi('bishe:finalexamscoredistribution:edit')")
    @Log(title = "期末考试各题支撑目标分数分配", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Finalexamscoredistribution finalexamscoredistribution) {
        Integer courseId = finalexamscoredistribution.getCourseId();
        int sum = 0;
        if (finalexamscoredistribution.getFirQue() != null)
            sum += finalexamscoredistribution.getFirQue().intValue();
        if (finalexamscoredistribution.getSecQue() != null)
            sum += finalexamscoredistribution.getSecQue().intValue();
        if (finalexamscoredistribution.getThiQue() != null)
            sum += finalexamscoredistribution.getThiQue().intValue();
        if (finalexamscoredistribution.getFouQue() != null)
            sum += finalexamscoredistribution.getFouQue().intValue();
        if (finalexamscoredistribution.getFifQue() != null)
            sum += finalexamscoredistribution.getFifQue().intValue();
        if (finalexamscoredistribution.getSixQue() != null)
            sum += finalexamscoredistribution.getSixQue().intValue();
        if (finalexamscoredistribution.getSevQue() != null)
            sum += finalexamscoredistribution.getSevQue().intValue();
        if (finalexamscoredistribution.getEigQue() != null)
            sum += finalexamscoredistribution.getEigQue().intValue();
        if (finalexamscoredistribution.getNinQue() != null)
            sum += finalexamscoredistribution.getNinQue().intValue();
        if (finalexamscoredistribution.getTenQue() != null)
            sum += finalexamscoredistribution.getTenQue().intValue();
        finalexamscoredistribution.setMaxSco(BigDecimal.valueOf(sum));
        int i = finalexamscoredistributionService.updateFinalexamscoredistribution(finalexamscoredistribution);


        BigDecimal firTotal = finalexamscoredistributionService.countFirQue(courseId);
        BigDecimal secTotal = finalexamscoredistributionService.countSecQue(courseId);
        BigDecimal thiTotal = finalexamscoredistributionService.countThiQue(courseId);
        BigDecimal fouTotal = finalexamscoredistributionService.countFouQue(courseId);
        BigDecimal fifTotal = finalexamscoredistributionService.countFifQue(courseId);
        BigDecimal sixTotal = finalexamscoredistributionService.countSixQue(courseId);
        BigDecimal sevTotal = finalexamscoredistributionService.countSevQue(courseId);
        BigDecimal eigTotal = finalexamscoredistributionService.countEigQue(courseId);
        BigDecimal ninTotal = finalexamscoredistributionService.countNinQue(courseId);
        BigDecimal tenTotal = finalexamscoredistributionService.countTenQue(courseId);
        Auxiliarycalculation auxiliarycalculation = new Auxiliarycalculation();
        auxiliarycalculation.setCourseId(courseId);
        if (firTotal == null) {
            auxiliarycalculation.setFirMax(BigDecimal.valueOf(0));
        } else {
            auxiliarycalculation.setFirMax(firTotal);
        }
        if (secTotal == null) {
            auxiliarycalculation.setSecMax(BigDecimal.valueOf(0));
        } else {
            auxiliarycalculation.setSecMax(secTotal);
        }
        if (thiTotal == null) {
            auxiliarycalculation.setThiMax(BigDecimal.valueOf(0));
        } else {
            auxiliarycalculation.setThiMax(thiTotal);
        }
        if (fouTotal == null) {
            auxiliarycalculation.setFouMax(BigDecimal.valueOf(0));
        } else {
            auxiliarycalculation.setFouMax(fouTotal);
        }
        if (fifTotal == null) {
            auxiliarycalculation.setFifMax(BigDecimal.valueOf(0));
        } else {
            auxiliarycalculation.setFifMax(fifTotal);
        }
        if (sixTotal == null) {
            auxiliarycalculation.setSixMax(BigDecimal.valueOf(0));
        } else {
            auxiliarycalculation.setSixMax(sixTotal);
        }
        if (sevTotal == null) {
            auxiliarycalculation.setSevMax(BigDecimal.valueOf(0));
        } else {
            auxiliarycalculation.setFirMax(sevTotal);
        }
        if (eigTotal == null) {
            auxiliarycalculation.setEigMax(BigDecimal.valueOf(0));
        } else {
            auxiliarycalculation.setEigMax(eigTotal);
        }
        if (ninTotal == null) {
            auxiliarycalculation.setNinMax(BigDecimal.valueOf(0));
        } else {
            auxiliarycalculation.setNinMax(ninTotal);
        }
        if (tenTotal == null) {
            auxiliarycalculation.setTenMax(BigDecimal.valueOf(0));
        } else {
            auxiliarycalculation.setTenMax(tenTotal);
        }
        auxiliarycalculationService.updateAuxiliarycalculation(auxiliarycalculation);

        return toAjax(i);
    }


    /**
     * 删除期末考试各题支撑目标分数分配
     */
    @PreAuthorize("@ss.hasPermi('bishe:finalexamscoredistribution:remove')")
    @Log(title = "期末考试各题支撑目标分数分配", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public AjaxResult remove(Integer courseId,String courseObjectives) {
        return toAjax(finalexamscoredistributionService.deleteFinalexamscoredistributionByCourseIdAndObj(courseId,courseObjectives));
    }

}
