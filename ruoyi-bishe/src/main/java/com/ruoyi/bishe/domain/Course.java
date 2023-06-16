package com.ruoyi.bishe.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 总课程表对象 tb_course
 * 
 * @author suzhonghui
 * @date 2023-03-19
 */
public class Course extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课号 */
    private Integer courseId;

    /** 课程名字 */
    @Excel(name = "课程名字")
    private String courseName;

    /* 学期 */
    @Excel(name = "学年")
    private String term;
    /** 学期 */
    @Excel(name = "学期")
    private String xueqi;

    /** 教师名字 */
    @Excel(name = "教师名字")
    private String teacherName;

    /** 专业 */
    @Excel(name = "专业")
    private String major;

    /** 学时 */
    @Excel(name = "学时")
    private Integer classHour;

    /** 学分 */
    @Excel(name = "学分")
    private Double credit;

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", term='" + term + '\'' +
                ", xueqi='" + xueqi + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", major='" + major + '\'' +
                ", classHour=" + classHour +
                ", credit=" + credit +
                '}';
    }

    public String getXueqi() {
        return xueqi;
    }

    public void setXueqi(String xueqi) {
        this.xueqi = xueqi;
    }

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
    public void setTerm(String term) 
    {
        this.term = term;
    }

    public String getTerm() 
    {
        return term;
    }
    public void setTeacherName(String teacherName) 
    {
        this.teacherName = teacherName;
    }

    public String getTeacherName() 
    {
        return teacherName;
    }
    public void setMajor(String major) 
    {
        this.major = major;
    }

    public String getMajor() 
    {
        return major;
    }
    public void setClassHour(Integer classHour) 
    {
        this.classHour = classHour;
    }

    public Integer getClassHour() 
    {
        return classHour;
    }
    public void setCredit(Double credit) 
    {
        this.credit = credit;
    }

    public Double getCredit() 
    {
        return credit;
    }

}
