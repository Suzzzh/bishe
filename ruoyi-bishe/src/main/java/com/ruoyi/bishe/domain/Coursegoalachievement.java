package com.ruoyi.bishe.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 课程目标达成度表对象 tb_coursegoalachievement
 * 
 * @author ruoyi
 * @date 2023-03-22
 */
public class Coursegoalachievement extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课号 */
    @Excel(name = "课号")
    private Integer courseId;

    /** 课程目标 */
    @Excel(name = "课程目标")
    private String courseObjectives;

    /** 第一大题比例 */
    @Excel(name = "第一大题比例")
    private BigDecimal firRatio;

    /** 第二大题比例 */
    @Excel(name = "第二大题比例")
    private BigDecimal secRatio;

    /** 第三大题比例 */
    @Excel(name = "第三大题比例")
    private BigDecimal thiRatio;

    /** 第四大题比例 */
    @Excel(name = "第四大题比例")
    private BigDecimal fouRatio;

    /** 第五大题比例 */
    @Excel(name = "第五大题比例")
    private BigDecimal fifRatio;

    /** 第六大题比例 */
    @Excel(name = "第六大题比例")
    private BigDecimal sixRatio;

    /** 第七大题比例 */
    @Excel(name = "第七大题比例")
    private BigDecimal sevRatio;

    /** 第八大题比例 */
    @Excel(name = "第八大题比例")
    private BigDecimal eigRatio;

    /** 第九大题比例 */
    @Excel(name = "第九大题比例")
    private BigDecimal ninRatio;

    /** 第十大题比例 */
    @Excel(name = "第十大题比例")
    private BigDecimal tenRatio;

    /** 期中成绩比例 */
    @Excel(name = "期中成绩比例")
    private BigDecimal midtermRatio;

    /** 作业成绩比例 */
    @Excel(name = "作业成绩比例")
    private BigDecimal homeworkRatio;

    /** 课堂测验比例 */
    @Excel(name = "课堂测验比例")
    private BigDecimal quizRatio;

    /** 大作业比例 */
    @Excel(name = "大作业比例")
    private BigDecimal largeRatio;

    /** 实验比例 */
    @Excel(name = "实验比例")
    private BigDecimal experimentRatio;

    /** 验收/答辩比例 */
    @Excel(name = "验收/答辩比例")
    private BigDecimal replyRatio;

    /** 课程报告比例/论文 */
    @Excel(name = "课程报告比例/论文")
    private BigDecimal thesisRatio;

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
    public void setFirRatio(BigDecimal firRatio) 
    {
        this.firRatio = firRatio;
    }

    public BigDecimal getFirRatio() 
    {
        return firRatio;
    }
    public void setSecRatio(BigDecimal secRatio) 
    {
        this.secRatio = secRatio;
    }

    public BigDecimal getSecRatio() 
    {
        return secRatio;
    }
    public void setThiRatio(BigDecimal thiRatio) 
    {
        this.thiRatio = thiRatio;
    }

    public BigDecimal getThiRatio() 
    {
        return thiRatio;
    }
    public void setFouRatio(BigDecimal fouRatio) 
    {
        this.fouRatio = fouRatio;
    }

    public BigDecimal getFouRatio() 
    {
        return fouRatio;
    }
    public void setFifRatio(BigDecimal fifRatio) 
    {
        this.fifRatio = fifRatio;
    }

    public BigDecimal getFifRatio() 
    {
        return fifRatio;
    }
    public void setSixRatio(BigDecimal sixRatio) 
    {
        this.sixRatio = sixRatio;
    }

    public BigDecimal getSixRatio() 
    {
        return sixRatio;
    }
    public void setSevRatio(BigDecimal sevRatio) 
    {
        this.sevRatio = sevRatio;
    }

    public BigDecimal getSevRatio() 
    {
        return sevRatio;
    }
    public void setEigRatio(BigDecimal eigRatio) 
    {
        this.eigRatio = eigRatio;
    }

    public BigDecimal getEigRatio() 
    {
        return eigRatio;
    }
    public void setNinRatio(BigDecimal ninRatio) 
    {
        this.ninRatio = ninRatio;
    }

    public BigDecimal getNinRatio() 
    {
        return ninRatio;
    }
    public void setTenRatio(BigDecimal tenRatio) 
    {
        this.tenRatio = tenRatio;
    }

    public BigDecimal getTenRatio() 
    {
        return tenRatio;
    }
    public void setMidtermRatio(BigDecimal midtermRatio) 
    {
        this.midtermRatio = midtermRatio;
    }

    public BigDecimal getMidtermRatio() 
    {
        return midtermRatio;
    }
    public void setHomeworkRatio(BigDecimal homeworkRatio) 
    {
        this.homeworkRatio = homeworkRatio;
    }

    public BigDecimal getHomeworkRatio() 
    {
        return homeworkRatio;
    }
    public void setQuizRatio(BigDecimal quizRatio) 
    {
        this.quizRatio = quizRatio;
    }

    public BigDecimal getQuizRatio() 
    {
        return quizRatio;
    }
    public void setLargeRatio(BigDecimal largeRatio) 
    {
        this.largeRatio = largeRatio;
    }

    public BigDecimal getLargeRatio() 
    {
        return largeRatio;
    }
    public void setExperimentRatio(BigDecimal experimentRatio) 
    {
        this.experimentRatio = experimentRatio;
    }

    public BigDecimal getExperimentRatio() 
    {
        return experimentRatio;
    }
    public void setReplyRatio(BigDecimal replyRatio) 
    {
        this.replyRatio = replyRatio;
    }

    public BigDecimal getReplyRatio() 
    {
        return replyRatio;
    }
    public void setThesisRatio(BigDecimal thesisRatio) 
    {
        this.thesisRatio = thesisRatio;
    }

    public BigDecimal getThesisRatio() 
    {
        return thesisRatio;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("courseId", getCourseId())
            .append("courseObjectives", getCourseObjectives())
            .append("firRatio", getFirRatio())
            .append("secRatio", getSecRatio())
            .append("thiRatio", getThiRatio())
            .append("fouRatio", getFouRatio())
            .append("fifRatio", getFifRatio())
            .append("sixRatio", getSixRatio())
            .append("sevRatio", getSevRatio())
            .append("eigRatio", getEigRatio())
            .append("ninRatio", getNinRatio())
            .append("tenRatio", getTenRatio())
            .append("midtermRatio", getMidtermRatio())
            .append("homeworkRatio", getHomeworkRatio())
            .append("quizRatio", getQuizRatio())
            .append("largeRatio", getLargeRatio())
            .append("experimentRatio", getExperimentRatio())
            .append("replyRatio", getReplyRatio())
            .append("thesisRatio", getThesisRatio())
            .toString();
    }
}
