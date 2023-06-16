package com.ruoyi.bishe.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 课程文件信息对象 tb_document
 * 
 * @author suzhonghui
 * @date 2023-05-15
 */
public class Document extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课程文件id */
    @Excel(name = "课程文件id")
    private Integer id;

    /** 课程文件名称 */
    @Excel(name = "课程文件名称")
    private String documentName;

    /** 上传者id */
    private Long uploadUserId;

    /** 上传者名字 */
    @Excel(name = "上传者名字")
    private String uploadUserName;

    /** 接收者id */
    private Integer receiveUserId;

    /** 接收者名字 */
    @Excel(name = "接收者名字")
    private String receiveUserName;

    /** 文件路径 */
    private String path;

    /** 文件所属专业 */
    @Excel(name = "文件所属专业")
    private String major;

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
    public void setDocumentName(String documentName) 
    {
        this.documentName = documentName;
    }

    public String getDocumentName() 
    {
        return documentName;
    }
    public void setUploadUserId(Long uploadUserId)
    {
        this.uploadUserId = uploadUserId;
    }

    public Long getUploadUserId()
    {
        return uploadUserId;
    }
    public void setUploadUserName(String uploadUserName) 
    {
        this.uploadUserName = uploadUserName;
    }

    public String getUploadUserName() 
    {
        return uploadUserName;
    }
    public void setReceiveUserId(Integer receiveUserId)
    {
        this.receiveUserId = receiveUserId;
    }

    public Integer getReceiveUserId()
    {
        return receiveUserId;
    }
    public void setReceiveUserName(String receiveUserName) 
    {
        this.receiveUserName = receiveUserName;
    }

    public String getReceiveUserName() 
    {
        return receiveUserName;
    }
    public void setPath(String path) 
    {
        this.path = path;
    }

    public String getPath() 
    {
        return path;
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
            .append("id", getId())
            .append("documentName", getDocumentName())
            .append("uploadUserId", getUploadUserId())
            .append("uploadUserName", getUploadUserName())
            .append("receiveUserId", getReceiveUserId())
            .append("receiveUserName", getReceiveUserName())
            .append("path", getPath())
            .append("major", getMajor())
            .toString();
    }
}
