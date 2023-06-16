package com.ruoyi.bishe.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 评价方式及成绩比例对象 tb_emsp
 * 
 * @author suzhonghui
 * @date 2023-03-22
 */
public class Emsp extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课号 */
    @Excel(name = "课号")
    private Integer courseId;

    /** 课程目标 */
    @Excel(name = "课程目标")
    private String courseObjectives;

    /** 支撑毕业要求指标点 */
    @Excel(name = "支撑毕业要求指标点")
    private String requirementPoints;

    /** 期中成绩 */
    @Excel(name = "期中成绩")
    private BigDecimal midtermSco;

    /** 作业成绩 */
    @Excel(name = "作业成绩")
    private BigDecimal homeworkSco;

    /** 课堂测验 */
    @Excel(name = "课堂测验")
    private BigDecimal quizSco;

    /** 大作业 */
    @Excel(name = "大作业")
    private BigDecimal largeSco;

    /** 实验 */
    @Excel(name = "实验")
    private BigDecimal experimentSco;

    /** 验收/答辩 */
    @Excel(name = "验收/答辩")
    private BigDecimal replySco;

    /** 课程报告/论文 */
    @Excel(name = "课程报告/论文")
    private BigDecimal thesisSco;

    /** 期末考试 */
    @Excel(name = "期末考试")
    private BigDecimal finalSco;

    /** 成绩比例 */
    @Excel(name = "成绩比例")
    private BigDecimal ratio;

    public void setCourseId(Integer courseId) 
    {
        this.courseId = courseId;
    }

    public Integer getCourseId() 
    {
        return courseId;
    }
    public void setCourseObjectives(String courseObjectives) 
    {
        this.courseObjectives = courseObjectives;
    }

    public String getCourseObjectives() 
    {
        return courseObjectives;
    }
    public void setRequirementPoints(String requirementPoints) 
    {
        this.requirementPoints = requirementPoints;
    }

    public String getRequirementPoints() 
    {
        return requirementPoints;
    }
    public void setMidtermSco(BigDecimal midtermSco) 
    {
        this.midtermSco = midtermSco;
    }

    public BigDecimal getMidtermSco() 
    {
        return midtermSco;
    }
    public void setHomeworkSco(BigDecimal homeworkSco) 
    {
        this.homeworkSco = homeworkSco;
    }

    public BigDecimal getHomeworkSco() 
    {
        return homeworkSco;
    }
    public void setQuizSco(BigDecimal quizSco) 
    {
        this.quizSco = quizSco;
    }

    public BigDecimal getQuizSco() 
    {
        return quizSco;
    }
    public void setLargeSco(BigDecimal largeSco) 
    {
        this.largeSco = largeSco;
    }

    public BigDecimal getLargeSco() 
    {
        return largeSco;
    }
    public void setExperimentSco(BigDecimal experimentSco) 
    {
        this.experimentSco = experimentSco;
    }

    public BigDecimal getExperimentSco() 
    {
        return experimentSco;
    }
    public void setReplySco(BigDecimal replySco) 
    {
        this.replySco = replySco;
    }

    public BigDecimal getReplySco() 
    {
        return replySco;
    }
    public void setThesisSco(BigDecimal thesisSco) 
    {
        this.thesisSco = thesisSco;
    }

    public BigDecimal getThesisSco() 
    {
        return thesisSco;
    }
    public void setFinalSco(BigDecimal finalSco) 
    {
        this.finalSco = finalSco;
    }

    public BigDecimal getFinalSco() 
    {
        return finalSco;
    }
    public void setRatio(BigDecimal ratio) 
    {
        this.ratio = ratio;
    }

    public BigDecimal getRatio() 
    {
        return ratio;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("courseId", getCourseId())
            .append("courseObjectives", getCourseObjectives())
            .append("requirementPoints", getRequirementPoints())
            .append("midtermSco", getMidtermSco())
            .append("homeworkSco", getHomeworkSco())
            .append("quizSco", getQuizSco())
            .append("largeSco", getLargeSco())
            .append("experimentSco", getExperimentSco())
            .append("replySco", getReplySco())
            .append("thesisSco", getThesisSco())
            .append("finalSco", getFinalSco())
            .append("ratio", getRatio())
            .toString();
    }
}
