package com.ruoyi.bishe.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 优秀学生名单对象 tb_excellent
 * 
 * @author suzhonghui
 * @date 2023-05-11
 */
public class Excellent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课号 */
    @Excel(name = "课号")
    private Integer courseId;

    /** 课程名字 */
    @Excel(name = "课程名字")
    private String courseName;

    /** 学生学号 */
    @Excel(name = "学生学号")
    private String studentNumber;

    /** 学生姓名 */
    @Excel(name = "学生姓名")
    private String studentName;

    /** 课程专业 */
    @Excel(name = "课程专业")
    private String major;

    public void setCourseId(Integer courseId) 
    {
        this.courseId = courseId;
    }

    public Integer getCourseId() 
    {
        return courseId;
    }
    public void setCourseName(String courseName) 
    {
        this.courseName = courseName;
    }

    public String getCourseName() 
    {
        return courseName;
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
    public void setMajor(String major) 
    {
        this.major = major;
    }

    public String getMajor() 
    {
        return major;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("courseId", getCourseId())
            .append("courseName", getCourseName())
            .append("studentNumber", getStudentNumber())
            .append("studentName", getStudentName())
            .append("major", getMajor())
            .toString();
    }
}
