package com.ruoyi.web.controller.bishe;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.bishe.domain.*;
import com.ruoyi.bishe.service.*;
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
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 评价方式及成绩比例Controller
 *
 * @author suzhonghui
 * @date 2023-03-22
 */
@RestController
@RequestMapping("/bishe/emsp")
public class EmspController extends BaseController {
    @Autowired
    private IEmspService emspService;

    @Autowired
    private IFinalexamscoredistributionService finalexamscoredistributionService;
    @Autowired
    private IEvaluationpahseService evaluationpahseService;

    @Autowired
    private ICoursegoalachievementService coursegoalachievementService;
    @Autowired
    private ICourseService courseService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysDeptService sysDeptService;

    /**
     * 查询评价方式及成绩比例列表
     */
    @PreAuthorize("@ss.hasPermi('bishe:emsp:list')")
    @GetMapping("/list")
    public TableDataInfo list(Emsp emsp) {
        startPage();

        Long userId = getUserId();
        if (userId == 1L) {
            List<Emsp> list = emspService.selectEmspList(emsp);
            return getDataTable(list);
        }
        if (userId == 2 || userId == 100 || userId == 101 || userId == 102 || userId == 103 || userId == 104) {
            String major = sysDeptService.selectDeptNameById(getDeptId());
            List<Integer> courseIdList = courseService.selectCourseIdListByMajor(major);
            List<Emsp> list = emspService.selectEmspByCourseIdList(courseIdList);
            return getDataTable(list);
        }
        List<Integer> courseIdList = courseService.selectCourseIdListByName(sysUserService.selectNickNameById(userId));
        List<Emsp> list = emspService.selectEmspByCourseIdList(courseIdList);


        return getDataTable(list);
    }

    /**
     * 导出评价方式及成绩比例列表
     */
    @PreAuthorize("@ss.hasPermi('bishe:emsp:export')")
    @Log(title = "评价方式及成绩比例", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Emsp emsp) {
        List<Emsp> list = emspService.selectEmspList(emsp);
        ExcelUtil<Emsp> util = new ExcelUtil<Emsp>(Emsp.class);
        util.exportExcel(response, list, "评价方式及成绩比例数据");
    }

    /**
     * 获取评价方式及成绩比例详细信息
     */
    @PreAuthorize("@ss.hasPermi('bishe:emsp:query')")
    @PostMapping("/find")
    public AjaxResult getInfo(Integer courseId, String courseObjectives) {
        return success(emspService.selectEmspByCourseId(courseId, courseObjectives));
    }

    /**
     * 新增评价方式及成绩比例
     */
    @PreAuthorize("@ss.hasPermi('bishe:emsp:add')")
    @Log(title = "评价方式及成绩比例", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Emsp emsp) {
        Integer courseId = emsp.getCourseId();
        String courseObjectives = emsp.getCourseObjectives();
        BigDecimal midtermSco = emsp.getMidtermSco();
        BigDecimal homeworkSco = emsp.getHomeworkSco();
        BigDecimal quizSco = emsp.getQuizSco();
        BigDecimal largeSco = emsp.getLargeSco();
        BigDecimal experimentSco = emsp.getExperimentSco();
        BigDecimal replySco = emsp.getReplySco();
        BigDecimal thesisSco = emsp.getThesisSco();
        Evaluationpahse evaluationpahse = evaluationpahseService.selectEvaluationpahseByCourseId(courseId);
        Finalexamscoredistribution finalexamscoredistribution = finalexamscoredistributionService.selectMaxScoByCourseIdandObjectives(courseId, courseObjectives);

        /*final表的卷面满分  O4*/
        BigDecimal maxSco = finalexamscoredistribution.getMaxSco();
        BigDecimal firQue = finalexamscoredistribution.getFirQue();
        BigDecimal secQue = finalexamscoredistribution.getSecQue();
        BigDecimal thiQue = finalexamscoredistribution.getThiQue();
        BigDecimal fouQue = finalexamscoredistribution.getFouQue();
        BigDecimal fifQue = finalexamscoredistribution.getFifQue();
        BigDecimal sixQue = finalexamscoredistribution.getSixQue();
        BigDecimal sevQue = finalexamscoredistribution.getSevQue();
        BigDecimal eigQue = finalexamscoredistribution.getEigQue();
        BigDecimal ninQue = finalexamscoredistribution.getNinQue();
        BigDecimal tenQue = finalexamscoredistribution.getTenQue();

        /*evalua表中的期末考试    */
        BigDecimal finalSco = evaluationpahse.getFinalSco();

        /*emsp表中的期末考试成绩比例  K  */
        BigDecimal result = (finalSco.multiply(maxSco)).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        /*sum为emsp表中期中成绩---课程报告的总分和*/
        int sum = 0;
        if (emsp.getMidtermSco() != null)
            sum += emsp.getMidtermSco().intValue();
        if (emsp.getHomeworkSco() != null)
            sum += emsp.getHomeworkSco().intValue();
        if (emsp.getQuizSco() != null)
            sum += emsp.getQuizSco().intValue();
        if (emsp.getLargeSco() != null)
            sum += emsp.getLargeSco().intValue();
        if (emsp.getExperimentSco() != null)
            sum += emsp.getExperimentSco().intValue();
        if (emsp.getReplySco() != null)
            sum += emsp.getReplySco().intValue();
        if (emsp.getThesisSco() != null)
            sum += emsp.getThesisSco().intValue();


        emsp.setFinalSco(result);

        /*emsp中的L*/
        BigDecimal ratio = result.add(BigDecimal.valueOf(sum));
        emsp.setRatio(ratio);
        /*2.59*/
        BigDecimal temp = null;
        if (!(maxSco.compareTo(BigDecimal.ZERO)==0))
            temp = (result.divide(ratio.multiply(maxSco), 6, RoundingMode.HALF_UP)).multiply(BigDecimal.valueOf(100));


        Coursegoalachievement coursegoalachievement = new Coursegoalachievement();
        coursegoalachievement.setCourseId(courseId);
        coursegoalachievement.setCourseObjectives(courseObjectives);
        if (firQue != null && firQue.compareTo(BigDecimal.ZERO)!=0)
            coursegoalachievement.setFirRatio(firQue.multiply(temp));
        if (secQue != null && secQue.compareTo(BigDecimal.ZERO)!=0)
            coursegoalachievement.setSecRatio(secQue.multiply(temp));
        if (thiQue != null  && thiQue.compareTo(BigDecimal.ZERO)!=0)
            coursegoalachievement.setThiRatio(thiQue.multiply(temp));
        if (fouQue != null  && fouQue.compareTo(BigDecimal.ZERO)!=0)
            coursegoalachievement.setFouRatio(fouQue.multiply(temp));
        if (fifQue != null  && fifQue.compareTo(BigDecimal.ZERO)!=0)
            coursegoalachievement.setFifRatio(fifQue.multiply(temp));
        if (sixQue != null  && sixQue.compareTo(BigDecimal.ZERO)!=0)
            coursegoalachievement.setSixRatio(sixQue.multiply(temp));
        if (sevQue != null  && sevQue.compareTo(BigDecimal.ZERO)!=0)
            coursegoalachievement.setSevRatio(sevQue.multiply(temp));
        if (eigQue != null  && eigQue.compareTo(BigDecimal.ZERO)!=0)
            coursegoalachievement.setEigRatio(eigQue.multiply(temp));
        if (ninQue != null  && ninQue.compareTo(BigDecimal.ZERO)!=0)
            coursegoalachievement.setNinRatio(ninQue.multiply(temp));
        if (tenQue != null  && tenQue.compareTo(BigDecimal.ZERO)!=0)
            coursegoalachievement.setTenRatio(tenQue.multiply(temp));
        if (midtermSco != null  && midtermSco.compareTo(BigDecimal.ZERO)!=0)
            coursegoalachievement.setMidtermRatio((midtermSco.divide(ratio, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))));
        if (homeworkSco != null  && homeworkSco.compareTo(BigDecimal.ZERO)!=0)
            coursegoalachievement.setHomeworkRatio(homeworkSco.divide(ratio, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)));
        if (quizSco != null  && quizSco.compareTo(BigDecimal.ZERO)!=0)
            coursegoalachievement.setQuizRatio((quizSco.divide(ratio, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))));
        if (largeSco != null  && largeSco.compareTo(BigDecimal.ZERO)!=0)
            coursegoalachievement.setLargeRatio((largeSco.divide(ratio, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))));
        if (experimentSco != null && experimentSco.compareTo(BigDecimal.ZERO)!=0)
            coursegoalachievement.setExperimentRatio((experimentSco.divide(ratio, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))));
        if (replySco != null  && replySco.compareTo(BigDecimal.ZERO)!=0)
            coursegoalachievement.setReplyRatio((replySco.divide(ratio, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))));
        if (thesisSco != null && thesisSco.compareTo(BigDecimal.ZERO)!=0)
            coursegoalachievement.setThesisRatio((thesisSco.divide(ratio, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))));

        coursegoalachievementService.insertCoursegoalachievement(coursegoalachievement);

        return toAjax(emspService.insertEmsp(emsp));
    }

    /**
     * 修改评价方式及成绩比例
     */
    @PreAuthorize("@ss.hasPermi('bishe:emsp:edit')")
    @Log(title = "评价方式及成绩比例", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Emsp emsp) {
        Integer courseId = emsp.getCourseId();
        String courseObjectives = emsp.getCourseObjectives();
        BigDecimal midtermSco = emsp.getMidtermSco();
        BigDecimal homeworkSco = emsp.getHomeworkSco();
        BigDecimal quizSco = emsp.getQuizSco();
        BigDecimal largeSco = emsp.getLargeSco();
        BigDecimal experimentSco = emsp.getExperimentSco();
        BigDecimal replySco = emsp.getReplySco();
        BigDecimal thesisSco = emsp.getThesisSco();
        Evaluationpahse evaluationpahse = evaluationpahseService.selectEvaluationpahseByCourseId(courseId);
        Finalexamscoredistribution finalexamscoredistribution = finalexamscoredistributionService.selectMaxScoByCourseIdandObjectives(courseId, courseObjectives);

        /*final表的卷面满分  O4*/
        BigDecimal maxSco = finalexamscoredistribution.getMaxSco();
        BigDecimal firQue = finalexamscoredistribution.getFirQue();
        BigDecimal secQue = finalexamscoredistribution.getSecQue();
        BigDecimal thiQue = finalexamscoredistribution.getThiQue();
        BigDecimal fouQue = finalexamscoredistribution.getFouQue();
        BigDecimal fifQue = finalexamscoredistribution.getFifQue();
        BigDecimal sixQue = finalexamscoredistribution.getSixQue();
        BigDecimal sevQue = finalexamscoredistribution.getSevQue();
        BigDecimal eigQue = finalexamscoredistribution.getEigQue();
        BigDecimal ninQue = finalexamscoredistribution.getNinQue();
        BigDecimal tenQue = finalexamscoredistribution.getTenQue();

        /*evalua表中的期末考试    */
        BigDecimal finalSco = evaluationpahse.getFinalSco();

        /*emsp表中的期末考试成绩比例  K  */
        BigDecimal result = (finalSco.multiply(maxSco)).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        /*sum为emsp表中期中成绩---课程报告的总分和*/
        int sum = 0;
        if (emsp.getMidtermSco() != null)
            sum += emsp.getMidtermSco().intValue();
        if (emsp.getHomeworkSco() != null)
            sum += emsp.getHomeworkSco().intValue();
        if (emsp.getQuizSco() != null)
            sum += emsp.getQuizSco().intValue();
        if (emsp.getLargeSco() != null)
            sum += emsp.getLargeSco().intValue();
        if (emsp.getExperimentSco() != null)
            sum += emsp.getExperimentSco().intValue();
        if (emsp.getReplySco() != null)
            sum += emsp.getReplySco().intValue();
        if (emsp.getThesisSco() != null)
            sum += emsp.getThesisSco().intValue();


        emsp.setFinalSco(result);

        /*emsp中的L*/
        BigDecimal ratio = result.add(BigDecimal.valueOf(sum));
        emsp.setRatio(ratio);
        /*2.59*/
        BigDecimal temp = null;
        if (!(maxSco.compareTo(BigDecimal.ZERO)==0))
            temp = (result.divide(ratio.multiply(maxSco), 6, RoundingMode.HALF_UP)).multiply(BigDecimal.valueOf(100));


        Coursegoalachievement coursegoalachievement = new Coursegoalachievement();
        coursegoalachievement.setCourseId(courseId);
        coursegoalachievement.setCourseObjectives(courseObjectives);
        if (firQue != null)
            coursegoalachievement.setFirRatio(firQue.multiply(temp));
        if (secQue != null)
            coursegoalachievement.setSecRatio(secQue.multiply(temp));
        if (thiQue != null)
            coursegoalachievement.setThiRatio(thiQue.multiply(temp));
        if (fouQue != null)
            coursegoalachievement.setFouRatio(fouQue.multiply(temp));
        if (fifQue != null)
            coursegoalachievement.setFifRatio(fifQue.multiply(temp));
        if (sixQue != null)
            coursegoalachievement.setSixRatio(sixQue.multiply(temp));
        if (sevQue != null)
            coursegoalachievement.setSevRatio(sevQue.multiply(temp));
        if (eigQue != null)
            coursegoalachievement.setEigRatio(eigQue.multiply(temp));
        if (ninQue != null)
            coursegoalachievement.setNinRatio(ninQue.multiply(temp));
        if (tenQue != null)
            coursegoalachievement.setTenRatio(tenQue.multiply(temp));
        if (midtermSco != null)
            coursegoalachievement.setMidtermRatio((midtermSco.divide(ratio, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))));
        if (homeworkSco != null)
            coursegoalachievement.setHomeworkRatio(homeworkSco.divide(ratio, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)));
        if (quizSco != null)
            coursegoalachievement.setQuizRatio((quizSco.divide(ratio, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))));
        if (largeSco != null)
            coursegoalachievement.setLargeRatio((largeSco.divide(ratio, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))));
        if (experimentSco != null)
            coursegoalachievement.setExperimentRatio((experimentSco.divide(ratio, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))));
        if (replySco != null)
            coursegoalachievement.setReplyRatio((replySco.divide(ratio, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))));
        if (thesisSco != null)
            coursegoalachievement.setThesisRatio((thesisSco.divide(ratio, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))));

        coursegoalachievementService.insertCoursegoalachievement(coursegoalachievement);

        return toAjax(emspService.updateEmsp(emsp));
    }

    /**
     * 删除评价方式及成绩比例
     */
    @PreAuthorize("@ss.hasPermi('bishe:emsp:remove')")
    @Log(title = "评价方式及成绩比例", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public AjaxResult remove(Integer courseId,String courseObjectives) {
        return toAjax(emspService.deleteEmspByCourseIdAndObj(courseId,courseObjectives));
    }
}
