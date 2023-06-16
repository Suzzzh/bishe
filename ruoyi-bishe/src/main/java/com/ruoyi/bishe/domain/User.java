package com.ruoyi.bishe.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户管理对象 tb_user
 * 
 * @author ruoyi
 * @date 2023-03-19
 */
public class User extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户id */
    private Integer userId;

    /** 用户名字 */
    @Excel(name = "用户名字")
    private String username;

    /** 用户密码 */
    @Excel(name = "用户密码")
    private String password;

    /** 用户角色 */
    @Excel(name = "用户角色")
    private Integer role;

    /** 上级id */
    @Excel(name = "上级id")
    private Integer fatherId;

    /** 专业 */
    @Excel(name = "专业")
    private String major;

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public Integer getUserId()
    {
        return userId;
    }
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setRole(Integer role)
    {
        this.role = role;
    }

    public Integer getRole()
    {
        return role;
    }
    public void setFatherId(Integer fatherId)
    {
        this.fatherId = fatherId;
    }

    public Integer getFatherId()
    {
        return fatherId;
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
            .append("userId", getUserId())
            .append("username", getUsername())
            .append("password", getPassword())
            .append("role", getRole())
            .append("fatherId", getFatherId())
            .append("major", getMajor())
            .toString();
    }
}
