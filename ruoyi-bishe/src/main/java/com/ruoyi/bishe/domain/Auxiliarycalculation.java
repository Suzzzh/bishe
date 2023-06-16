package com.ruoyi.bishe.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 辅助计算表对象 tb_auxiliarycalculation
 * 
 * @author suzhonghui
 * @date 2023-03-22
 */
public class Auxiliarycalculation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课号 */
    @Excel(name = "课号")
    private Integer courseId;

    /** 第一大题满分 */
    @Excel(name = "第一大题满分")
    private BigDecimal firMax;

    /** 第二大题满分 */
    @Excel(name = "第二大题满分")
    private BigDecimal secMax;

    /** 第三大题满分 */
    @Excel(name = "第三大题满分")
    private BigDecimal thiMax;

    /** 第四大题满分 */
    @Excel(name = "第四大题满分")
    private BigDecimal fouMax;

    /** 第五大题满分 */
    @Excel(name = "第五大题满分")
    private BigDecimal fifMax;

    /** 第六大题满分 */
    @Excel(name = "第六大题满分")
    private BigDecimal sixMax;

    /** 第七大题满分 */
    @Excel(name = "第七大题满分")
    private BigDecimal sevMax;

    /** 第八大题满分 */
    @Excel(name = "第八大题满分")
    private BigDecimal eigMax;

    /** 第九大题满分 */
    @Excel(name = "第九大题满分")
    private BigDecimal ninMax;

    /** 第十大题满分 */
    @Excel(name = "第十大题满分")
    private BigDecimal tenMax;

    /** 期中成绩满分 */
    @Excel(name = "期中成绩满分")
    private BigDecimal midtermMax;

    /** 作业成绩满分 */
    @Excel(name = "作业成绩满分")
    private BigDecimal homeworkMax;

    /** 课堂测验满分 */
    @Excel(name = "课堂测验满分")
    private BigDecimal quizMax;

    /** 大作业满分 */
    @Excel(name = "大作业满分")
    private BigDecimal largeMax;

    /** 实验满分 */
    @Excel(name = "实验满分")
    private BigDecimal experimentMax;

    /** 验收/答辩满分 */
    @Excel(name = "验收/答辩满分")
    private BigDecimal replyMax;

    /** 课程报告/论文满分 */
    @Excel(name = "课程报告/论文满分")
    private BigDecimal thesisMax;

    public void setCourseId(Integer courseId) 
    {
        this.courseId = courseId;
    }

    public Integer getCourseId() 
    {
        return courseId;
    }
    public void setFirMax(BigDecimal firMax) 
    {
        this.firMax = firMax;
    }

    public BigDecimal getFirMax() 
    {
        return firMax;
    }
    public void setSecMax(BigDecimal secMax) 
    {
        this.secMax = secMax;
    }

    public BigDecimal getSecMax() 
    {
        return secMax;
    }
    public void setThiMax(BigDecimal thiMax) 
    {
        this.thiMax = thiMax;
    }

    public BigDecimal getThiMax() 
    {
        return thiMax;
    }
    public void setFouMax(BigDecimal fouMax) 
    {
        this.fouMax = fouMax;
    }

    public BigDecimal getFouMax() 
    {
        return fouMax;
    }
    public void setFifMax(BigDecimal fifMax) 
    {
        this.fifMax = fifMax;
    }

    public BigDecimal getFifMax() 
    {
        return fifMax;
    }
    public void setSixMax(BigDecimal sixMax) 
    {
        this.sixMax = sixMax;
    }

    public BigDecimal getSixMax() 
    {
        return sixMax;
    }
    public void setSevMax(BigDecimal sevMax) 
    {
        this.sevMax = sevMax;
    }

    public BigDecimal getSevMax() 
    {
        return sevMax;
    }
    public void setEigMax(BigDecimal eigMax) 
    {
        this.eigMax = eigMax;
    }

    public BigDecimal getEigMax() 
    {
        return eigMax;
    }
    public void setNinMax(BigDecimal ninMax) 
    {
        this.ninMax = ninMax;
    }

    public BigDecimal getNinMax() 
    {
        return ninMax;
    }
    public void setTenMax(BigDecimal tenMax) 
    {
        this.tenMax = tenMax;
    }

    public BigDecimal getTenMax() 
    {
        return tenMax;
    }
    public void setMidtermMax(BigDecimal midtermMax) 
    {
        this.midtermMax = midtermMax;
    }

    public BigDecimal getMidtermMax() 
    {
        return midtermMax;
    }
    public void setHomeworkMax(BigDecimal homeworkMax) 
    {
        this.homeworkMax = homeworkMax;
    }

    public BigDecimal getHomeworkMax() 
    {
        return homeworkMax;
    }
    public void setQuizMax(BigDecimal quizMax) 
    {
        this.quizMax = quizMax;
    }

    public BigDecimal getQuizMax() 
    {
        return quizMax;
    }
    public void setLargeMax(BigDecimal largeMax) 
    {
        this.largeMax = largeMax;
    }

    public BigDecimal getLargeMax() 
    {
        return largeMax;
    }
    public void setExperimentMax(BigDecimal experimentMax) 
    {
        this.experimentMax = experimentMax;
    }

    public BigDecimal getExperimentMax() 
    {
        return experimentMax;
    }
    public void setReplyMax(BigDecimal replyMax) 
    {
        this.replyMax = replyMax;
    }

    public BigDecimal getReplyMax() 
    {
        return replyMax;
    }
    public void setThesisMax(BigDecimal thesisMax) 
    {
        this.thesisMax = thesisMax;
    }

    public BigDecimal getThesisMax() 
    {
        return thesisMax;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("courseId", getCourseId())
            .append("firMax", getFirMax())
            .append("secMax", getSecMax())
            .append("thiMax", getThiMax())
            .append("fouMax", getFouMax())
            .append("fifMax", getFifMax())
            .append("sixMax", getSixMax())
            .append("sevMax", getSevMax())
            .append("eigMax", getEigMax())
            .append("ninMax", getNinMax())
            .append("tenMax", getTenMax())
            .append("midtermMax", getMidtermMax())
            .append("homeworkMax", getHomeworkMax())
            .append("quizMax", getQuizMax())
            .append("largeMax", getLargeMax())
            .append("experimentMax", getExperimentMax())
            .append("replyMax", getReplyMax())
            .append("thesisMax", getThesisMax())
            .toString();
    }
}
