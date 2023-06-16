package com.ruoyi.bishe.mapper;

import java.util.List;
import com.ruoyi.bishe.domain.Document;

/**
 * 课程文件信息Mapper接口
 * 
 * @author suzhonghui
 * @date 2023-05-15
 */
public interface DocumentMapper 
{
    /**
     * 查询课程文件信息
     * 
     * @param id 课程文件信息主键
     * @return 课程文件信息
     */
    public Document selectDocumentById(Long id);

    /**
     * 查询课程文件信息列表
     * 
     * @param document 课程文件信息
     * @return 课程文件信息集合
     */
    public List<Document> selectDocumentList(Document document);

    /**
     * 新增课程文件信息
     * 
     * @param document 课程文件信息
     * @return 结果
     */
    public int insertDocument(Document document);

    /**
     * 修改课程文件信息
     * 
     * @param document 课程文件信息
     * @return 结果
     */
    public int updateDocument(Document document);

    /**
     * 删除课程文件信息
     * 
     * @param id 课程文件信息主键
     * @return 结果
     */
    public int deleteDocumentById(Long id);

    /**
     * 批量删除课程文件信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDocumentByIds(Long[] ids);

    public String selectDocumentNameById(Integer id);
}
