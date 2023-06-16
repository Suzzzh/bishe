package com.ruoyi.web.controller.bishe;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

import com.ruoyi.bishe.domain.Evaluationpahse;
import com.ruoyi.bishe.service.ICourseService;
import com.ruoyi.common.core.domain.model.LoginUser;
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
import com.ruoyi.bishe.domain.Inputscore;
import com.ruoyi.bishe.service.IInputscoreService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 输入学生成绩Controller
 * 
 * @author suzhonghui
 * @date 2023-04-03
 */
@RestController
@RequestMapping("/bishe/inputscore")
public class InputscoreController extends BaseController {
    @Autowired
    private IInputscoreService inputscoreService;
    @Autowired
    private Validator validator;
    @Autowired
    private ICourseService courseService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysDeptService sysDeptService;

    /**
     * 查询输入学生成绩列表
     */
    @PreAuthorize("@ss.hasPermi('bishe:inputscore:list')")
    @GetMapping("/list")
    public TableDataInfo list(Inputscore inputscore) {
        startPage();

        Long userId = getUserId();
        if (userId == 1L) {
            List<Inputscore> list = inputscoreService.selectInputscoreList(inputscore);
            return getDataTable(list);
        }
        if (userId == 2 || userId == 100 || userId == 101 || userId == 102 || userId == 103 || userId == 104) {
            String major = sysDeptService.selectDeptNameById(getDeptId());
            List<Integer> courseIdList = courseService.selectCourseIdListByMajor(major);
            List<Inputscore> list = inputscoreService.selectEvaluationpahseByCourseIdList(courseIdList);
            return getDataTable(list);
        }
        List<Integer> courseIdList = courseService.selectCourseIdListByName(sysUserService.selectNickNameById(userId));
        List<Inputscore> list = inputscoreService.selectEvaluationpahseByCourseIdList(courseIdList);
        return getDataTable(list);
    }

    /**
     * 导出输入学生成绩列表
     */
    @PreAuthorize("@ss.hasPermi('bishe:inputscore:export')")
    @Log(title = "输入学生成绩", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Inputscore inputscore) {
        List<Inputscore> list = inputscoreService.selectInputscoreList(inputscore);
        ExcelUtil<Inputscore> util = new ExcelUtil<Inputscore>(Inputscore.class);
        util.exportExcel(response, list, "输入学生成绩数据");
    }

    /**
     * 获取输入学生成绩详细信息
     */
    @PreAuthorize("@ss.hasPermi('bishe:inputscore:query')")
    /* @GetMapping(value = "/{id}")*/
    @PostMapping("/find")
    public AjaxResult getInfo(Integer id, String num) {
        return success(inputscoreService.selectInputscoreByIdandNum(id, num));
    }

    /**
     * 新增输入学生成绩
     */
    @PreAuthorize("@ss.hasPermi('bishe:inputscore:add')")
    @Log(title = "输入学生成绩", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Inputscore inputscore) {
        return toAjax(inputscoreService.insertInputscore(inputscore));
    }

    /**
     * 修改输入学生成绩
     */
    @PreAuthorize("@ss.hasPermi('bishe:inputscore:edit')")
    @Log(title = "输入学生成绩", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Inputscore inputscore) {
        return toAjax(inputscoreService.updateInputscore(inputscore));
    }

    /**
     * 删除输入学生成绩
     */
    @PreAuthorize("@ss.hasPermi('bishe:inputscore:remove')")
    @Log(title = "输入学生成绩", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public AjaxResult remove(Integer id,String num) {
        return toAjax(inputscoreService.deleteInputscoreByIdAndNum(id,num));
    }

    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('bishe:inputscore:importData')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        System.out.println(file);
        ExcelUtil<Inputscore> util = new ExcelUtil<Inputscore>(Inputscore.class);
        List<Inputscore> inputscoresList = util.importExcel(file.getInputStream());
        LoginUser loginUser = getLoginUser();
        String operName = loginUser.getUsername();
        String message = inputscoreService.importUser(inputscoresList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<Inputscore> util = new ExcelUtil<Inputscore>(Inputscore.class);
        return util.importTemplateExcel("用户数据");
    }


}

