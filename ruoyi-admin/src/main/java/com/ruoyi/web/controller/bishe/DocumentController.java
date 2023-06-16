package com.ruoyi.web.controller.bishe;

import java.io.File;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysUserService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.bishe.domain.Document;
import com.ruoyi.bishe.service.IDocumentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 课程文件信息Controller
 * 
 * @author suzhonghui
 * @date 2023-05-15
 */
@RestController
@RequestMapping("/bishe/document")
public class DocumentController extends BaseController
{
    @Autowired
    private IDocumentService documentService;

    @Autowired
    private ISysDeptService sysDeptService;
    @Autowired
    private ISysUserService sysUserService;
    /**
     * 查询课程文件信息列表
     */
    @PreAuthorize("@ss.hasPermi('bishe:document:list')")
    @GetMapping("/list")
    public TableDataInfo list(Document document)
    {
        startPage();
        Long userId = getUserId();
        Long deptId = getDeptId();
        if (userId==1L){
            List<Document> list = documentService.selectDocumentList(document);
            return getDataTable(list);
        }
        if (userId == 2 ||userId == 100|| userId == 101 || userId == 102 || userId == 103 || userId == 104){
            document.setMajor(sysDeptService.selectDeptNameById(deptId));
            List<Document> list = documentService.selectDocumentList(document);
            return getDataTable(list);
        }
        if(userId!=1L){
            document.setUploadUserName(getUsername());
            List<Document> list = documentService.selectDocumentList(document);
            return getDataTable(list);
        }
        List<?> list=null;
        return getDataTable(list);
    }

    /**
     * 导出课程文件信息列表
     */
    @PreAuthorize("@ss.hasPermi('bishe:document:export')")
    @Log(title = "课程文件信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Document document)
    {
        List<Document> list = documentService.selectDocumentList(document);
        ExcelUtil<Document> util = new ExcelUtil<Document>(Document.class);
        util.exportExcel(response, list, "课程文件信息数据");
    }

    /**
     * 获取课程文件信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('bishe:document:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(documentService.selectDocumentById(id));
    }

    /**
     * 新增课程文件信息
     */
    @PreAuthorize("@ss.hasPermi('bishe:document:add')")
    @Log(title = "课程文件信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Document document)
    {
        return toAjax(documentService.insertDocument(document));
    }

    /**
     * 修改课程文件信息
     */
    @PreAuthorize("@ss.hasPermi('bishe:document:edit')")
    @Log(title = "课程文件信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Document document)
    {
        return toAjax(documentService.updateDocument(document));
    }

    /**
     * 删除课程文件信息
     */
    @PreAuthorize("@ss.hasPermi('bishe:document:remove')")
    @Log(title = "课程文件信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(documentService.deleteDocumentByIds(ids));
    }



    private String uploadPath="d:/ruoyi/uploadPath/document";
    /**
     * 课程文件上传部分
     */
    @PostMapping("/upload")
    @PreAuthorize("@ss.hasPermi('bishe:document:upload')")
    public AjaxResult upload(MultipartFile file, boolean updateSupport) throws Exception {
        Document document=new Document();
        Integer receiveId = null;
        Long deptId = getDeptId();
        if(deptId==103) receiveId=2;
        if(deptId==104) receiveId=100;
        if(deptId==105) receiveId=101;
        if(deptId==106) receiveId=102;
        if(deptId==107) receiveId=103;
        if(deptId==200) receiveId=104;
        if (file.isEmpty()){
            return error("文件为空");
        }
        //文件名
        String fileName=file.getOriginalFilename();
        String  suffixName= fileName.substring(fileName.lastIndexOf("."));
        String filePath= uploadPath+"/"+fileName;
        String fileName1="d:/ruoyi/uploadPath/document/质量.docx";
        /*获取文件名称+格式,例如：质量.docx*/
        String  suffixName1= fileName1.substring(fileName1.lastIndexOf("/")+1);
        /*获取文件名称,例如：质量*/
        String  suffixName2 = fileName1.substring(fileName1.lastIndexOf("/")+1,fileName1.lastIndexOf("."));
        File fileTempObj = new File(uploadPath+"/"+fileName);
        //检测目录是否存在
        if (!fileTempObj.getParentFile().exists()){
            fileTempObj.getParentFile().mkdirs();
        }
        //使用文件名检测文件是否已经存在
        File file1=new File(filePath);
        if(file1.exists()){
            file.transferTo(fileTempObj);
            return success("文件上传成功");
        }
        try{
            file.transferTo(fileTempObj);
            document.setDocumentName(fileName);
            document.setUploadUserId(getUserId());
            document.setUploadUserName(getUsername());
            document.setReceiveUserId(receiveId);
            document.setReceiveUserName(sysUserService.selectUserNameById(receiveId));
            document.setPath(filePath);
            document.setMajor(sysDeptService.selectDeptNameById(deptId));
            documentService.insertDocument(document);
        }
        catch (Exception e){
            return error("error"+e.getMessage());
        }
        return success("文件上传成功");
    }


    /**
     * 课程文件下载部分
     */
    private String downloadPath="d:/ruoyi/uploadPath/document";
    @PreAuthorize("@ss.hasPermi('bishe:document:download')")
    @PostMapping("/download")
    public AjaxResult download(HttpServletResponse response,Integer Id) throws Exception {
        String fileName=documentService.selectFileNameById(Id);
        System.out.println(fileName);
        File file=new File(uploadPath+'/'+fileName);
        if(!file.exists()){
            return error("下载的文件不存在！");
        }
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int)file.length());
        response.setHeader("Content-Disposition","attachment;filename="+fileName);
        byte[] readBytes = FileCopyUtils.copyToByteArray(file);
        OutputStream os=response.getOutputStream();
        os.write(readBytes);
        return success("文件下载成功！");
    }
}
