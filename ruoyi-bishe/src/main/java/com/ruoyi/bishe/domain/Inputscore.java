package com.ruoyi.bishe.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 输入学生成绩对象 tb_inputscore
 * 
 * @author suzhonghui
 * @date 2023-04-03
 */
public class Inputscore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课号 */
    @Excel(name = "课号")
    private Integer id;

    /** 学号 */
    @Excel(name = "学号")
    private String studentNumber;

    /** 学生名字 */
    @Excel(name = "学生名字")
    private String studentName;

    /** 第一大题成绩 */
    @Excel(name = "第一大题成绩")
    private BigDecimal firQue;

    /** 第二大题成绩 */
    @Excel(name = "第二大题成绩")
    private BigDecimal secQue;

    /** 第三大题成绩 */
    @Excel(name = "第三大题成绩")
    private BigDecimal thiQue;

    /** 第四大题成绩 */
    @Excel(name = "第四大题成绩")
    private BigDecimal fouQue;

    /** 第五大题成绩 */
    @Excel(name = "第五大题成绩")
    private BigDecimal fifQue;

    /** 第六大题成绩 */
    @Excel(name = "第六大题成绩")
    private BigDecimal sixQue;

    /** 第七答题成绩 */
    @Excel(name = "第七答题成绩")
    private BigDecimal sevQue;

    /** 第八答题成绩 */
    @Excel(name = "第八答题成绩")
    private BigDecimal eigQue;

    /** 第九答题成绩 */
    @Excel(name = "第九答题成绩")
    private BigDecimal ninQue;

    /** 第十大题成绩 */
    @Excel(name = "第十大题成绩")
    private BigDecimal tenQue;

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

    /** 考试成绩 */
    @Excel(name = "考试成绩")
    private BigDecimal finalSco;

    /** 总评成绩 */
    @Excel(name = "总评成绩")
    private BigDecimal totalSco;

    /** 成绩类别 */
    @Excel(name = "成绩类别")
    private String type;

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
    public void setStudentNumber(String studentNumber) 
    {
        this.studentNumber = studentNumber;
    }

    public String getStudentNumber() 
    {
        return studentNumber;
    }
    public void setStudentName(String studentName) 
    {
        this.studentName = studentName;
    }

    public String getStudentName() 
    {
        return studentName;
    }
    public void setFirQue(BigDecimal firQue) 
    {
        this.firQue = firQue;
    }

    public BigDecimal getFirQue() 
    {
        return firQue;
    }
    public void setSecQue(BigDecimal secQue) 
    {
        this.secQue = secQue;
    }

    public BigDecimal getSecQue() 
    {
        return secQue;
    }
    public void setThiQue(BigDecimal thiQue) 
    {
        this.thiQue = thiQue;
    }

    public BigDecimal getThiQue() 
    {
        return thiQue;
    }
    public void setFouQue(BigDecimal fouQue) 
    {
        this.fouQue = fouQue;
    }

    public BigDecimal getFouQue() 
    {
        return fouQue;
    }
    public void setFifQue(BigDecimal fifQue) 
    {
        this.fifQue = fifQue;
    }

    public BigDecimal getFifQue() 
    {
        return fifQue;
    }
    public void setSixQue(BigDecimal sixQue) 
    {
        this.sixQue = sixQue;
    }

    public BigDecimal getSixQue() 
    {
        return sixQue;
    }
    public void setSevQue(BigDecimal sevQue) 
    {
        this.sevQue = sevQue;
    }

    public BigDecimal getSevQue() 
    {
        return sevQue;
    }
    public void setEigQue(BigDecimal eigQue) 
    {
        this.eigQue = eigQue;
    }

    public BigDecimal getEigQue() 
    {
        return eigQue;
    }
    public void setNinQue(BigDecimal ninQue) 
    {
        this.ninQue = ninQue;
    }

    public BigDecimal getNinQue() 
    {
        return ninQue;
    }
    public void setTenQue(BigDecimal tenQue) 
    {
        this.tenQue = tenQue;
    }

    public BigDecimal getTenQue() 
    {
        return tenQue;
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
    public void setTotalSco(BigDecimal totalSco) 
    {
        this.totalSco = totalSco;
    }

    public BigDecimal getTotalSco() 
    {
        return totalSco;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("studentNumber", getStudentNumber())
            .append("studentName", getStudentName())
            .append("firQue", getFirQue())
            .append("secQue", getSecQue())
            .append("thiQue", getThiQue())
            .append("fouQue", getFouQue())
            .append("fifQue", getFifQue())
            .append("sixQue", getSixQue())
            .append("sevQue", getSevQue())
            .append("eigQue", getEigQue())
            .append("ninQue", getNinQue())
            .append("tenQue", getTenQue())
            .append("midtermSco", getMidtermSco())
            .append("homeworkSco", getHomeworkSco())
            .append("quizSco", getQuizSco())
            .append("largeSco", getLargeSco())
            .append("experimentSco", getExperimentSco())
            .append("replySco", getReplySco())
            .append("thesisSco", getThesisSco())
            .append("finalSco", getFinalSco())
            .append("totalSco", getTotalSco())
            .append("type", getType())
            .toString();
    }
}
