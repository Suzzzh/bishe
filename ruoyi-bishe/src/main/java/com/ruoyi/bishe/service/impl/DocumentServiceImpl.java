package com.ruoyi.bishe.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bishe.mapper.DocumentMapper;
import com.ruoyi.bishe.domain.Document;
import com.ruoyi.bishe.service.IDocumentService;

/**
 * 课程文件信息Service业务层处理
 * 
 * @author suzhonghui
 * @date 2023-05-15
 */
@Service
public class DocumentServiceImpl implements IDocumentService 
{
    @Autowired
    private DocumentMapper documentMapper;

    /**
     * 查询课程文件信息
     * 
     * @param id 课程文件信息主键
     * @return 课程文件信息
     */
    @Override
    public Document selectDocumentById(Long id)
    {
        return documentMapper.selectDocumentById(id);
    }

    /**
     * 查询课程文件信息列表
     * 
     * @param document 课程文件信息
     * @return 课程文件信息
     */
    @Override
    public List<Document> selectDocumentList(Document document)
    {
        return documentMapper.selectDocumentList(document);
    }

    /**
     * 新增课程文件信息
     * 
     * @param document 课程文件信息
     * @return 结果
     */
    @Override
    public int insertDocument(Document document)
    {
        return documentMapper.insertDocument(document);
    }

    /**
     * 修改课程文件信息
     * 
     * @param document 课程文件信息
     * @return 结果
     */
    @Override
    public int updateDocument(Document document)
    {
        return documentMapper.updateDocument(document);
    }

    /**
     * 批量删除课程文件信息
     * 
     * @param ids 需要删除的课程文件信息主键
     * @return 结果
     */
    @Override
    public int deleteDocumentByIds(Long[] ids)
    {
        return documentMapper.deleteDocumentByIds(ids);
    }

    /**
     * 删除课程文件信息信息
     * 
     * @param id 课程文件信息主键
     * @return 结果
     */
    @Override
    public int deleteDocumentById(Long id)
    {
        return documentMapper.deleteDocumentById(id);
    }

    public String selectFileNameById(Integer id) {

        return documentMapper.selectDocumentNameById(id);
    }
}
