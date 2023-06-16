package com.ruoyi.bishe.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 期末考试各题支撑目标分数分配对象 tb_finalexamscoredistribution
 * 
 * @author suzhonghui
 * @date 2023-03-22
 */
public class Finalexamscoredistribution extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课号 */
    @Excel(name = "课号")
    private Integer courseId;

    /** 课程目标 */
    @Excel(name = "课程目标")
    private String courseObjectives;

    /** 卷面满分 */
    @Excel(name = "卷面满分")
    private BigDecimal maxSco;

    /** 第一大题 */
    @Excel(name = "第一大题")
    private BigDecimal firQue;

    /** 第二大题 */
    @Excel(name = "第二大题")
    private BigDecimal secQue;

    /** 第三大题 */
    @Excel(name = "第三大题")
    private BigDecimal thiQue;

    /** 第四大题 */
    @Excel(name = "第四大题")
    private BigDecimal fouQue;

    /** 第五大题 */
    @Excel(name = "第五大题")
    private BigDecimal fifQue;

    /** 第六大题 */
    @Excel(name = "第六大题")
    private BigDecimal sixQue;

    /** 第七大题 */
    @Excel(name = "第七大题")
    private BigDecimal sevQue;

    /** 第八大题 */
    @Excel(name = "第八大题")
    private BigDecimal eigQue;

    /** 第九大题 */
    @Excel(name = "第九大题")
    private BigDecimal ninQue;

    /** 第十大题 */
    @Excel(name = "第十大题")
    private BigDecimal tenQue;

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
    public void setMaxSco(BigDecimal maxSco) 
    {
        this.maxSco = maxSco;
    }

    public BigDecimal getMaxSco() 
    {
        return maxSco;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("courseId", getCourseId())
            .append("courseObjectives", getCourseObjectives())
            .append("maxSco", getMaxSco())
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
            .toString();
    }
}
