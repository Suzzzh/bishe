package com.ruoyi.bishe.service.impl;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.ruoyi.bishe.domain.Evaluationpahse;
import com.ruoyi.bishe.domain.Inputscore;
import com.ruoyi.bishe.domain.Zhiliang;
import com.ruoyi.bishe.mapper.EvaluationpahseMapper;
import com.ruoyi.bishe.mapper.InputscoreMapper;
import com.ruoyi.bishe.service.IEvaluationpahseService;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bishe.mapper.CourseMapper;
import com.ruoyi.bishe.domain.Course;
import com.ruoyi.bishe.service.ICourseService;

import javax.servlet.http.HttpServletResponse;

/**
 * 总课程表Service业务层处理
 *
 * @author suzhonghui
 * @date 2023-03-19
 */
@Service
public class CourseServiceImpl implements ICourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private EvaluationpahseMapper evaluationpahseMapper;
    @Autowired
    private InputscoreMapper inputscoreMapper;

    /**
     * 查询总课程表
     *
     * @param courseId 总课程表主键
     * @return 总课程表
     */
    @Override
    public Course selectCourseByCourseId(Integer courseId) {
        return courseMapper.selectCourseByCourseId(courseId);
    }

    /**
     * 查询总课程表列表
     *
     * @param course 总课程表
     * @return 总课程表
     */
    @Override
    public List<Course> selectCourseList(Course course) {
        return courseMapper.selectCourseList(course);
    }

    /**
     * 新增总课程表
     *
     * @param course 总课程表
     * @return 结果
     */
    @Override
    public int insertCourse(Course course) {
        return courseMapper.insertCourse(course);
    }

    /**
     * 修改总课程表
     *
     * @param course 总课程表
     * @return 结果
     */
    @Override
    public int updateCourse(Course course) {
        return courseMapper.updateCourse(course);
    }

    /**
     * 批量删除总课程表
     *
     * @param courseIds 需要删除的总课程表主键
     * @return 结果
     */
    @Override
    public int deleteCourseByCourseIds(Integer[] courseIds) {
        return courseMapper.deleteCourseByCourseIds(courseIds);
    }

    /**
     * 删除总课程表信息
     *
     * @param courseId 总课程表主键
     * @return 结果
     */
    @Override
    public int deleteCourseByCourseId(Integer courseId) {
        return courseMapper.deleteCourseByCourseId(courseId);
    }

    public void downloadZhiliang(HttpServletResponse response, Zhiliang zhiliang) throws XDocReportException, IOException {
        Integer courseId = zhiliang.getCourseId();
        //获取Word模板，模板存放路径在项目的resources目录下
        InputStream ins = this.getClass().getResourceAsStream("/test1.docx");
        //注册xdocreport实例并加载FreeMarker模板引擎
        IXDocReport report = XDocReportRegistry.getRegistry().loadReport(ins,
                TemplateEngineKind.Freemarker);
        //创建xdocreport上下文对象
        IContext context = report.createContext();
        Course course = courseMapper.selectCourseByCourseId(courseId);
        Evaluationpahse evaluationpahse = evaluationpahseMapper.selectEvaluationpahseByCourseId(courseId);
        BigDecimal highSco = inputscoreMapper.selectHighSco(courseId);
        BigDecimal lowSco = inputscoreMapper.selectLowSco(courseId);
        Integer ninNum1 = inputscoreMapper.selectninNum1(courseId);
        Integer eigNum1 = inputscoreMapper.selecteigNum1(courseId);
        Integer sevNum1 = inputscoreMapper.selectsevNum1(courseId);
        Integer sixNum1 = inputscoreMapper.selectsixNum1(courseId);
        Integer lowNum1 = inputscoreMapper.selectlowNum1(courseId);
        BigDecimal ninRatio1 = BigDecimal.valueOf(ninNum1).divide(BigDecimal.valueOf(zhiliang.getRealityNum()), 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
        BigDecimal eigRatio1 = BigDecimal.valueOf(eigNum1).divide(BigDecimal.valueOf(zhiliang.getRealityNum()), 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
        BigDecimal sevRatio1 = BigDecimal.valueOf(sevNum1).divide(BigDecimal.valueOf(zhiliang.getRealityNum()), 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
        BigDecimal sixRatio1 = BigDecimal.valueOf(sixNum1).divide(BigDecimal.valueOf(zhiliang.getRealityNum()), 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
        BigDecimal lowRatio1 = BigDecimal.valueOf(lowNum1).divide(BigDecimal.valueOf(zhiliang.getRealityNum()), 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
        BigDecimal passRatio = ninRatio1.add(eigRatio1).add(sevRatio1).add(sixRatio1);
        Integer ninNum2 = inputscoreMapper.selectninNum2(courseId);
        Integer eigNum2 = inputscoreMapper.selecteigNum2(courseId);
        Integer sevNum2 = inputscoreMapper.selectsevNum2(courseId);
        Integer sixNum2 = inputscoreMapper.selectsixNum2(courseId);
        Integer lowNum2 = inputscoreMapper.selectlowNum2(courseId);
        BigDecimal ninRatio2 = BigDecimal.valueOf(ninNum2).divide(BigDecimal.valueOf(zhiliang.getRealityNum()), 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
        BigDecimal eigRatio2 = BigDecimal.valueOf(eigNum2).divide(BigDecimal.valueOf(zhiliang.getRealityNum()), 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
        BigDecimal sevRatio2 = BigDecimal.valueOf(sevNum2).divide(BigDecimal.valueOf(zhiliang.getRealityNum()), 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
        BigDecimal sixRatio2 = BigDecimal.valueOf(sixNum2).divide(BigDecimal.valueOf(zhiliang.getRealityNum()), 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
        BigDecimal lowRatio2 = BigDecimal.valueOf(lowNum2).divide(BigDecimal.valueOf(zhiliang.getRealityNum()), 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
        //创建要替换的文本变量
        context.put("term", course.getTerm());
        context.put("xueqi", course.getXueqi());
        List<Zhiliang> zhiliangsList = new ArrayList<Zhiliang>();
        zhiliang.setTeacherName(course.getTeacherName());
        zhiliang.setCourseName(course.getCourseName());
        zhiliang.setHighSco(highSco);
        zhiliang.setLowSco(lowSco);
        zhiliang.setPassRatio(passRatio);
        if(zhiliang.getMidtermSco()==null) zhiliang.setMidtermSco(BigDecimal.valueOf(0));
        if(zhiliang.getHomeworkSco()==null) zhiliang.setHomeworkSco(BigDecimal.valueOf(0));
        if(zhiliang.getQuizSco()==null) zhiliang.setQuizSco(BigDecimal.valueOf(0));
        if(zhiliang.getLargeSco()==null) zhiliang.setLargeSco(BigDecimal.valueOf(0));
        if(zhiliang.getExperimentSco()==null) zhiliang.setExperimentSco(BigDecimal.valueOf(0));
        if(zhiliang.getReplySco()==null) zhiliang.setReplySco(BigDecimal.valueOf(0));
        if(zhiliang.getThesisSco()==null) zhiliang.setThesisSco(BigDecimal.valueOf(0));
        if(zhiliang.getFinalSco()==null) zhiliang.setFinalSco(BigDecimal.valueOf(0));
        if(zhiliang.getOther()==null) zhiliang.setOther(BigDecimal.valueOf(0));
        zhiliang.setNinNum1(ninNum1);
        zhiliang.setEigNum1(eigNum1);
        zhiliang.setSevNum1(sevNum1);
        zhiliang.setSixNum1(sixNum1);
        zhiliang.setLowNum1(lowNum1);
        zhiliang.setNinRatio1(ninRatio1);
        zhiliang.setEigRatio1(eigRatio1);
        zhiliang.setSevRatio1(sevRatio1);
        zhiliang.setSixRatio1(sixRatio1);
        zhiliang.setLowRatio1(lowRatio1);
        zhiliang.setNinNum2(ninNum2);
        zhiliang.setEigNum2(eigNum2);
        zhiliang.setSevNum2(sevNum2);
        zhiliang.setSixNum2(sixNum2);
        zhiliang.setLowNum2(lowNum2);
        zhiliang.setNinRatio2(ninRatio2);
        zhiliang.setEigRatio2(eigRatio2);
        zhiliang.setSevRatio2(sevRatio2);
        zhiliang.setSixRatio2(sixRatio2);
        zhiliang.setLowRatio2(lowRatio2);
        zhiliangsList.add(zhiliang);

        context.put("zhiliang", zhiliangsList);
        //创建字段元数据
        FieldsMetadata fm = report.createFieldsMetadata();
        //Word模板中的表格数据对应的集合类型
        fm.load("zhiliang", Zhiliang.class, true);

        //浏览器端下载
        response.setCharacterEncoding("utf-8");
        /*application/vnd.openxmlformats-officedocument.wordprocessingml.document*/
        /*application/msword*/
        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        String fileName = "生成模板测试.docx";
        response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8"))));
        report.process(context, response.getOutputStream());

    }

    @Override
    public String selectMajor(Long deptId) {
        return courseMapper.selectMajor(deptId);
    }

    public List<Integer> selectCourseIdListByName(String teachername){
        return courseMapper.selectCourseIdListByName(teachername);
    }

    public List<Integer> selectCourseIdListByMajor(String major) {
        return courseMapper.selectCourseIdListByMajor(major);
    }
}
