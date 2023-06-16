package com.ruoyi.bishe.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.ruoyi.bishe.domain.*;
import com.ruoyi.bishe.mapper.CourseMapper;
import com.ruoyi.bishe.service.*;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanValidators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bishe.mapper.InputscoreMapper;

import javax.validation.Validator;


/**
 * 输入学生成绩Service业务层处理
 *
 * @author suzhonghui
 * @date 2023-04-03
 */
@Service
public class InputscoreServiceImpl implements IInputscoreService {
    @Autowired
    private InputscoreMapper inputscoreMapper;
    @Autowired
    private IEvaluationpahseService evaluationpahseService;
    @Autowired
    private Validator validator;
    @Autowired
    private IEmspService emspService;
    @Autowired
    private IAuxiliarycalculationService auxiliarycalculationService;
    @Autowired
    private ICoursegoalachievementService coursegoalachievementService;
    @Autowired
    private IAchievedataService achievedataService;
    @Autowired
    private IExcellentService excellentService;
    @Autowired
    private IFailService failService;
    @Autowired
    private CourseMapper courseMapper;

    /**
     * 查询输入学生成绩
     *
     * @param id 输入学生成绩主键
     * @return 输入学生成绩
     */
    @Override
    public Inputscore selectInputscoreById(Integer id) {
        return inputscoreMapper.selectInputscoreById(id);
    }

    @Override
    public Inputscore selectInputscoreByIdandNum(Integer id, String num) {
        Inputscore inputscore = inputscoreMapper.selectInputscoreByIdandNum(id, num);
        System.out.println(inputscore);
        return inputscore;
    }

    /**
     * 查询输入学生成绩列表
     *
     * @param inputscore 输入学生成绩
     * @return 输入学生成绩
     */
    @Override
    public List<Inputscore> selectInputscoreList(Inputscore inputscore) {
        return inputscoreMapper.selectInputscoreList(inputscore);
    }

    /**
     * 新增输入学生成绩
     *
     * @param inputscore 输入学生成绩
     * @return 结果
     */
    @Override
    public int insertInputscore(Inputscore inputscore) {
        /*id 课号*/
        Integer id = inputscore.getId();
        Evaluationpahse evaluationpahse = evaluationpahseService.selectEvaluationpahseByCourseId(id);
        BigDecimal midtermratio = evaluationpahse.getMidtermSco();
        BigDecimal homeworkratio = evaluationpahse.getHomeworkSco();
        BigDecimal quizratio = evaluationpahse.getQuizSco();
        BigDecimal largeratio = evaluationpahse.getLargeSco();
        BigDecimal experimentratio = evaluationpahse.getExperimentSco();
        BigDecimal replyratio = evaluationpahse.getReplySco();
        BigDecimal thesisratio = evaluationpahse.getThesisSco();
        BigDecimal finalratio = evaluationpahse.getFinalSco();
        BigDecimal firQue = inputscore.getFirQue();
        BigDecimal secQue = inputscore.getSecQue();
        BigDecimal thiQue = inputscore.getThiQue();
        BigDecimal fouQue = inputscore.getFouQue();
        BigDecimal fifQue = inputscore.getFifQue();
        BigDecimal sixQue = inputscore.getSixQue();
        BigDecimal sevQue = inputscore.getSevQue();
        BigDecimal eigQue = inputscore.getEigQue();
        BigDecimal ninQue = inputscore.getNinQue();
        BigDecimal tenQue = inputscore.getTenQue();
        BigDecimal midtermSco = inputscore.getMidtermSco();
        BigDecimal homeworkSco = inputscore.getHomeworkSco();
        BigDecimal quizSco = inputscore.getQuizSco();
        BigDecimal largeSco = inputscore.getLargeSco();
        BigDecimal experimentSco = inputscore.getExperimentSco();
        BigDecimal replySco = inputscore.getReplySco();
        BigDecimal thesisSco = inputscore.getThesisSco();

        /*考试成绩*/
        BigDecimal sum = BigDecimal.valueOf(0);
        /*总评成绩*/
        BigDecimal total = BigDecimal.valueOf(0);
        if (firQue != null) {
            sum = sum.add(firQue);
        }
        if (secQue != null) {
            sum = sum.add(secQue);
        }
        if (thiQue != null) {
            sum = sum.add(thiQue);
        }
        if (fouQue != null) {
            sum = sum.add(fouQue);
        }
        if (fifQue != null) {
            sum = sum.add(fifQue);
        }
        if (sixQue != null) {
            sum = sum.add(sixQue);
        }
        if (sevQue != null) {
            sum = sum.add(sevQue);
        }
        if (eigQue != null) {
            sum = sum.add(eigQue);
        }
        if (ninQue != null) {
            sum = sum.add(ninQue);
        }
        if (tenQue != null) {
            sum = sum.add(tenQue);
        }
        if (midtermSco != null) {
            total = total.add(midtermSco.multiply(midtermratio));
        }
        if (homeworkSco != null) {
            total = total.add(homeworkSco.multiply(homeworkratio));
        }
        if (quizSco != null) {
            total = total.add(quizSco.multiply(quizratio));
        }
        if (largeSco != null) {
            total = total.add(largeSco.multiply(largeratio));
        }
        if (experimentSco != null) {
            total = total.add(experimentSco.multiply(experimentratio));
        }
        if (replySco != null) {
            total = total.add(replySco.multiply(replyratio));
        }
        if (thesisSco != null) {
            total = total.add(thesisSco.multiply(thesisratio));
        }
        inputscore.setFinalSco(sum);
        total = (total.add(sum.multiply(finalratio))).divide(BigDecimal.valueOf(100), 0, RoundingMode.HALF_UP);
        inputscore.setTotalSco(total);
        if (total.compareTo(BigDecimal.valueOf(90)) == 1) {
            inputscore.setType("优秀");
        } else if (total.compareTo(BigDecimal.valueOf(60)) == -1) {
            inputscore.setType("不及格");
        }
        int i = inputscoreMapper.insertInputscore(inputscore);


        if (i > 0) {
            /*插入成功后开始计算达成数据*/
            Achievedata achievedata = new Achievedata();
            achievedata.setId(id);
            achievedata.setStudentNumber(inputscore.getStudentNumber());
            achievedata.setStudentName(inputscore.getStudentName());
            Auxiliarycalculation auxiliarycalculation = auxiliarycalculationService.selectAuxiliarycalculationByCourseId(id);
            BigDecimal firCount = BigDecimal.valueOf(0);
            BigDecimal secCount = BigDecimal.valueOf(0);
            BigDecimal thiCount = BigDecimal.valueOf(0);
            BigDecimal fouCount = BigDecimal.valueOf(0);
            BigDecimal fifCount = BigDecimal.valueOf(0.0);
            BigDecimal sixCount = BigDecimal.valueOf(0);
            BigDecimal sevCount = BigDecimal.valueOf(0);
            BigDecimal eigCount = BigDecimal.valueOf(0);
            BigDecimal ninCount = BigDecimal.valueOf(0);
            BigDecimal tenCount = BigDecimal.valueOf(0);
            BigDecimal midtermCount = BigDecimal.valueOf(0);
            BigDecimal homeworkCount = BigDecimal.valueOf(0);
            BigDecimal quizCount = BigDecimal.valueOf(0);
            BigDecimal largeCount = BigDecimal.valueOf(0);
            BigDecimal experimentCount = BigDecimal.valueOf(0);
            BigDecimal replyCount = BigDecimal.valueOf(0);
            BigDecimal thesisCount = BigDecimal.valueOf(0);
            if ((auxiliarycalculation.getFirMax()).compareTo(BigDecimal.ZERO) == 0) {
                firCount = BigDecimal.valueOf(0.0);
            } else {
                firCount = BigDecimal.valueOf(100).divide(auxiliarycalculation.getFirMax(), 3, RoundingMode.HALF_UP);
            }
            if ((auxiliarycalculation.getSecMax().compareTo(BigDecimal.ZERO) == 0)) {
                secCount = BigDecimal.valueOf(0.0);
            } else {
                secCount = BigDecimal.valueOf(100).divide(auxiliarycalculation.getSecMax(), 3, RoundingMode.HALF_UP);
            }
            if ((auxiliarycalculation.getThiMax()).compareTo(BigDecimal.ZERO) == 0) {
                thiCount = BigDecimal.valueOf(0.0);
            } else {
                thiCount = BigDecimal.valueOf(100).divide(auxiliarycalculation.getThiMax(), 3, RoundingMode.HALF_UP);

            }
            if ((auxiliarycalculation.getFouMax()).compareTo(BigDecimal.ZERO) == 0) {
                fouCount = BigDecimal.valueOf(0.0);
            } else {
                fouCount = BigDecimal.valueOf(100).divide(auxiliarycalculation.getFouMax(), 3, RoundingMode.HALF_UP);
            }
            if ((auxiliarycalculation.getFifMax()).compareTo(BigDecimal.ZERO) == 0) {
                fifCount = BigDecimal.valueOf(0.0);
            } else {
                fifCount = BigDecimal.valueOf(100).divide(auxiliarycalculation.getFifMax(), 3, RoundingMode.HALF_UP);
            }
            if ((auxiliarycalculation.getSixMax()).compareTo(BigDecimal.ZERO) == 0) {
                sixCount = BigDecimal.valueOf(0.0);
            } else {
                sixCount = BigDecimal.valueOf(100).divide(auxiliarycalculation.getSixMax(), 3, RoundingMode.HALF_UP);
            }
            if ((auxiliarycalculation.getSevMax()).compareTo(BigDecimal.ZERO) == 0) {
                sevCount = BigDecimal.valueOf(0.0);
            } else {
                sevCount = BigDecimal.valueOf(100).divide(auxiliarycalculation.getSevMax(), 3, RoundingMode.HALF_UP);
            }
            if ((auxiliarycalculation.getEigMax()).compareTo(BigDecimal.ZERO) == 0) {
                eigCount = BigDecimal.valueOf(0.0);
            } else {
                eigCount = BigDecimal.valueOf(100).divide(auxiliarycalculation.getEigMax(), 3, RoundingMode.HALF_UP);
            }
            if ((auxiliarycalculation.getNinMax()).compareTo(BigDecimal.ZERO) == 0) {
                ninCount = BigDecimal.valueOf(0.0);
            } else {
                ninCount = BigDecimal.valueOf(100).divide(auxiliarycalculation.getNinMax(), 3, RoundingMode.HALF_UP);
            }
            if ((auxiliarycalculation.getTenMax()).compareTo(BigDecimal.ZERO) == 0) {
                tenCount = BigDecimal.valueOf(0.0);
            } else {
                tenCount = BigDecimal.valueOf(100).divide(auxiliarycalculation.getTenMax(), 3, RoundingMode.HALF_UP);
            }
            midtermCount = BigDecimal.valueOf(100).divide(auxiliarycalculation.getMidtermMax(), 3, RoundingMode.HALF_UP);
            homeworkCount = BigDecimal.valueOf(100).divide(auxiliarycalculation.getHomeworkMax(), 3, RoundingMode.HALF_UP);
            quizCount = BigDecimal.valueOf(100).divide(auxiliarycalculation.getQuizMax(), 3, RoundingMode.HALF_UP);
            largeCount = BigDecimal.valueOf(100).divide(auxiliarycalculation.getLargeMax(), 3, RoundingMode.HALF_UP);
            experimentCount = BigDecimal.valueOf(100).divide(auxiliarycalculation.getExperimentMax(), 3, RoundingMode.HALF_UP);
            replyCount = BigDecimal.valueOf(100).divide(auxiliarycalculation.getReplyMax(), 3, RoundingMode.HALF_UP);
            thesisCount = BigDecimal.valueOf(100).divide(auxiliarycalculation.getThesisMax(), 3, RoundingMode.HALF_UP);

            int times = emspService.selectCount(id);
            Coursegoalachievement coursegoalachievement = new Coursegoalachievement();
            for (int j = 1; j <= times; j++) {
                String courseobj = "课程目标" + j;
                BigDecimal temp = BigDecimal.valueOf(0);
                coursegoalachievement = coursegoalachievementService.selectDataByCourseIdandCourseObjectives(id, courseobj);
                BigDecimal firRatio = coursegoalachievement.getFirRatio();
                BigDecimal secRatio = coursegoalachievement.getSecRatio();
                BigDecimal thiRatio = coursegoalachievement.getThiRatio();
                BigDecimal fouRatio = coursegoalachievement.getFouRatio();
                BigDecimal fifRatio = coursegoalachievement.getFifRatio();
                BigDecimal sixRatio = coursegoalachievement.getSixRatio();
                BigDecimal sevRatio = coursegoalachievement.getSevRatio();
                BigDecimal eigRatio = coursegoalachievement.getEigRatio();
                BigDecimal ninRatio = coursegoalachievement.getNinRatio();
                BigDecimal tenRatio = coursegoalachievement.getTenRatio();
                BigDecimal midtermRatio = coursegoalachievement.getMidtermRatio();
                BigDecimal homeworkRatio = coursegoalachievement.getHomeworkRatio();
                BigDecimal quizRatio = coursegoalachievement.getQuizRatio();
                BigDecimal largeRatio = coursegoalachievement.getLargeRatio();
                BigDecimal experimentRatio = coursegoalachievement.getExperimentRatio();
                BigDecimal replyRatio = coursegoalachievement.getReplyRatio();
                BigDecimal thesisRatio = coursegoalachievement.getThesisRatio();
                if (firQue != null && firCount != null && firRatio != null) {
                    temp = temp.add(firQue.multiply(firCount.multiply(firRatio)));
                }
                if (secQue != null && secCount != null && secRatio != null) {
                    temp = temp.add(secQue.multiply(secCount.multiply(secRatio)));
                }
                if (thiQue != null && thiCount != null && thiRatio != null) {
                    temp = temp.add(thiQue.multiply(thiCount.multiply(thiRatio)));
                }
                if (fouQue != null && fouCount != null && fouRatio != null) {
                    temp = temp.add(fouQue.multiply(fouCount.multiply(fouRatio)));
                }
                if (fifQue != null && fifCount != null && fifRatio != null) {
                    temp = temp.add(fifQue.multiply(fifCount.multiply(fifRatio)));
                }
                if (sixQue != null && sixCount != null && sixRatio != null) {
                    temp = temp.add(sixQue.multiply(sixCount.multiply(sixRatio)));
                }
                if (sevQue != null && sevCount != null && sevRatio != null) {
                    temp = temp.add(sevQue.multiply(sevCount.multiply(sevRatio)));
                }
                if (eigQue != null && eigCount != null && eigRatio != null) {
                    temp = temp.add(eigQue.multiply(eigCount.multiply(eigRatio)));
                }
                if (ninQue != null && ninCount != null && ninRatio != null) {
                    temp = temp.add(ninQue.multiply(ninCount.multiply(ninRatio)));
                }
                if (tenQue != null && tenCount != null && tenRatio != null) {
                    temp = temp.add(tenQue.multiply(tenCount.multiply(tenRatio)));
                }
                if (midtermSco != null && midtermCount != null && midtermRatio != null) {
                    temp = temp.add(midtermSco.multiply(midtermCount.multiply(midtermRatio)));
                }
                if (homeworkSco != null && homeworkCount != null && homeworkRatio != null) {
                    temp = temp.add(homeworkSco.multiply(homeworkCount.multiply(homeworkRatio)));
                }
                if (quizSco != null && quizCount != null && quizRatio != null) {
                    temp = temp.add(quizSco.multiply(quizCount.multiply(quizRatio)));
                }
                if (largeSco != null && largeCount != null && largeRatio != null) {
                    temp = temp.add(largeSco.multiply(largeCount.multiply(largeRatio)));
                }
                if (experimentSco != null && experimentCount != null && experimentRatio != null) {
                    temp = temp.add(experimentSco.multiply(experimentCount.multiply(experimentRatio)));
                }
                if (replySco != null && replyCount != null && replyRatio != null) {
                    temp = temp.add(replySco.multiply(replyCount.multiply(replyRatio)));
                }
                if (thesisSco != null && thesisCount != null && thesisRatio != null) {
                    temp = temp.add(thesisSco.multiply(thesisCount.multiply(thesisRatio)));
                }


                if (j == 1)
                    achievedata.setCo1(temp.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP));
                if (j == 2)
                    achievedata.setCo2(temp.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP));
                if (j == 3)
                    achievedata.setCo3(temp.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP));
                if (j == 4)
                    achievedata.setCo4(temp.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP));
                if (j == 5)
                    achievedata.setCo5(temp.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP));
                if (j == 6)
                    achievedata.setCo6(temp.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP));
                if (j == 7)
                    achievedata.setCo7(temp.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP));
                if (j == 8)
                    achievedata.setCo8(temp.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP));
            }
            achievedataService.insertAchievedata(achievedata);

            //插入成功后，根据学生的成绩来对学生进行名单分析
            /*  a.compareTo(b)
             *   a>b  输出1
             *   a<b  输出-1
             *   a==b 输出0
             *  */
            /*   优秀学生名单插入*/
            Excellent excellent = new Excellent();
            if (total.compareTo(BigDecimal.valueOf(90)) >= 0) {
                excellent.setCourseId(id);
                excellent.setCourseName(courseMapper.selectCourseNameById(id));
                excellent.setStudentNumber(inputscore.getStudentNumber());
                excellent.setStudentName(inputscore.getStudentName());
                excellent.setMajor(courseMapper.selectCourseMajorById(id));
                excellentService.insertExcellent(excellent);
            }
            /*   优秀学生名单插入*/
            Fail fail = new Fail();
            if (total.compareTo(BigDecimal.valueOf(60)) < 0) {
                fail.setCourseId(id);
                fail.setCourseName(courseMapper.selectCourseNameById(id));
                System.out.println(inputscore);
                fail.setStudentNumber(inputscore.getStudentNumber());
                fail.setStudentName(inputscore.getStudentName());
                fail.setMajor(courseMapper.selectCourseMajorById(id));
                failService.insertFail(fail);
            }
        }

        return i;
    }

    /**
     * 修改输入学生成绩
     *
     * @param inputscore 输入学生成绩
     * @return 结果
     */
    @Override
    public int updateInputscore(Inputscore inputscore) {

        /*id 课号*/
        Integer id = inputscore.getId();
        Evaluationpahse evaluationpahse = evaluationpahseService.selectEvaluationpahseByCourseId(id);
        BigDecimal midtermratio = evaluationpahse.getMidtermSco();
        BigDecimal homeworkratio = evaluationpahse.getHomeworkSco();
        BigDecimal quizratio = evaluationpahse.getQuizSco();
        BigDecimal largeratio = evaluationpahse.getLargeSco();
        BigDecimal experimentratio = evaluationpahse.getExperimentSco();
        BigDecimal replyratio = evaluationpahse.getReplySco();
        BigDecimal thesisratio = evaluationpahse.getThesisSco();
        BigDecimal finalratio = evaluationpahse.getFinalSco();

        BigDecimal firQue = inputscore.getFirQue();
        BigDecimal secQue = inputscore.getSecQue();
        BigDecimal thiQue = inputscore.getThiQue();
        BigDecimal fouQue = inputscore.getFouQue();
        BigDecimal fifQue = inputscore.getFifQue();
        BigDecimal sixQue = inputscore.getSixQue();
        BigDecimal sevQue = inputscore.getSevQue();
        BigDecimal eigQue = inputscore.getEigQue();
        BigDecimal ninQue = inputscore.getNinQue();
        BigDecimal tenQue = inputscore.getTenQue();
        BigDecimal midtermSco = inputscore.getMidtermSco();
        BigDecimal homeworkSco = inputscore.getHomeworkSco();
        BigDecimal quizSco = inputscore.getQuizSco();
        BigDecimal largeSco = inputscore.getLargeSco();
        BigDecimal experimentSco = inputscore.getExperimentSco();
        BigDecimal replySco = inputscore.getReplySco();
        BigDecimal thesisSco = inputscore.getThesisSco();
        /*考试成绩*/
        BigDecimal sum = BigDecimal.valueOf(0);
        /*总评成绩*/
        BigDecimal total = BigDecimal.valueOf(0);
        if (firQue != null) {
            sum = sum.add(firQue);
        }
        if (secQue != null) {
            sum = sum.add(secQue);
        }
        if (thiQue != null) {
            sum = sum.add(thiQue);
        }
        if (fouQue != null) {
            sum = sum.add(fouQue);
        }
        if (fifQue != null) {
            sum = sum.add(fifQue);
        }
        if (sixQue != null) {
            sum = sum.add(sixQue);
        }
        if (sevQue != null) {
            sum = sum.add(sevQue);
        }
        if (eigQue != null) {
            sum = sum.add(eigQue);
        }
        if (ninQue != null) {
            sum = sum.add(ninQue);
        }
        if (tenQue != null) {
            sum = sum.add(tenQue);
        }
        if (midtermSco != null) {
            total = total.add(midtermSco.multiply(midtermratio));
        }
        if (homeworkSco != null) {
            total = total.add(homeworkSco.multiply(homeworkratio));
        }
        if (quizSco != null) {
            total = total.add(quizSco.multiply(quizratio));
        }
        if (largeSco != null) {
            total = total.add(largeSco.multiply(largeratio));
        }
        if (experimentSco != null) {
            total = total.add(experimentSco.multiply(experimentratio));
        }
        if (replySco != null) {
            total = total.add(replySco.multiply(replyratio));
        }
        if (thesisSco != null) {
            total = total.add(thesisSco.multiply(thesisratio));
        }
        inputscore.setFinalSco(sum);
        inputscore.setTotalSco((total.add(sum.multiply(finalratio))).divide(BigDecimal.valueOf(100), 0, RoundingMode.HALF_UP));

        return inputscoreMapper.updateInputscore(inputscore);
    }

    /**
     * 批量删除输入学生成绩
     *
     * @param ids 需要删除的输入学生成绩主键
     * @return 结果
     */
    @Override
    public int deleteInputscoreByIds(Long[] ids) {
        return inputscoreMapper.deleteInputscoreByIds(ids);
    }

    /**
     * 删除输入学生成绩信息
     *
     * @param id 输入学生成绩主键
     * @return 结果
     */
    @Override
    public int deleteInputscoreById(Long id) {
        return inputscoreMapper.deleteInputscoreById(id);
    }

    @Override
    public String importUser(List<Inputscore> inputscoresList, boolean updateSupport, String operName) {
        if (StringUtils.isNull(inputscoresList) || inputscoresList.size() == 0) {
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (Inputscore inputscore : inputscoresList) {
            try {
                BeanValidators.validateWithException(validator, inputscore);
                this.insertInputscore(inputscore);
                successNum++;

            } catch (Exception e) {
                failureNum++;
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();

    }
    @Override
    public int deleteInputscoreByIdAndNum(Integer courseId, String studentNum){
        return inputscoreMapper.deleteInputscoreByIdAndNum(courseId,studentNum);
    }

    @Override
    public List<Inputscore> selectEvaluationpahseByCourseIdList(List<Integer> courseIdList){
        return inputscoreMapper.selectEvaluationpahseByCourseIdList(courseIdList);
    }
}
