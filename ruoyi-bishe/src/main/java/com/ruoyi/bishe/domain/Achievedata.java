package com.ruoyi.bishe.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 生成达成数据对象 tb_achievedata
 * 
 * @author suzhonghui
 * @date 2023-04-03
 */
public class Achievedata extends BaseEntity
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

    /** 课程目标1达成数据 */
    @Excel(name = "课程目标1达成数据")
    private BigDecimal co1;

    /** 课程目标2达成数据 */
    @Excel(name = "课程目标2达成数据")
    private BigDecimal co2;

    /** 课程目标3达成数据 */
    @Excel(name = "课程目标3达成数据")
    private BigDecimal co3;

    /** 课程目标4达成数据 */
    @Excel(name = "课程目标4达成数据")
    private BigDecimal co4;

    /** 课程目标5达成数据 */
    @Excel(name = "课程目标5达成数据")
    private BigDecimal co5;

    /** 课程目标6达成数据 */
    @Excel(name = "课程目标6达成数据")
    private BigDecimal co6;

    /** 课程目标7达成数据 */
    @Excel(name = "课程目标7达成数据")
    private BigDecimal co7;

    /** 课程目标8达成数据 */
    @Excel(name = "课程目标8达成数据")
    private BigDecimal co8;

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
    public void setCo1(BigDecimal co1) 
    {
        this.co1 = co1;
    }

    public BigDecimal getCo1() 
    {
        return co1;
    }
    public void setCo2(BigDecimal co2) 
    {
        this.co2 = co2;
    }

    public BigDecimal getCo2() 
    {
        return co2;
    }
    public void setCo3(BigDecimal co3) 
    {
        this.co3 = co3;
    }

    public BigDecimal getCo3() 
    {
        return co3;
    }
    public void setCo4(BigDecimal co4) 
    {
        this.co4 = co4;
    }

    public BigDecimal getCo4() 
    {
        return co4;
    }
    public void setCo5(BigDecimal co5) 
    {
        this.co5 = co5;
    }

    public BigDecimal getCo5() 
    {
        return co5;
    }
    public void setCo6(BigDecimal co6) 
    {
        this.co6 = co6;
    }

    public BigDecimal getCo6() 
    {
        return co6;
    }
    public void setCo7(BigDecimal co7) 
    {
        this.co7 = co7;
    }

    public BigDecimal getCo7() 
    {
        return co7;
    }
    public void setCo8(BigDecimal co8) 
    {
        this.co8 = co8;
    }

    public BigDecimal getCo8() 
    {
        return co8;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("studentNumber", getStudentNumber())
            .append("studentName", getStudentName())
            .append("co1", getCo1())
            .append("co2", getCo2())
            .append("co3", getCo3())
            .append("co4", getCo4())
            .append("co5", getCo5())
            .append("co6", getCo6())
            .append("co7", getCo7())
            .append("co8", getCo8())
            .toString();
    }
}
