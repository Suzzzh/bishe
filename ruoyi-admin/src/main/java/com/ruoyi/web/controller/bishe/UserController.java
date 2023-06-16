package com.ruoyi.web.controller.bishe;


import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.http.HttpServletResponse;


import com.ruoyi.bishe.service.IExcellentService;
import com.ruoyi.common.utils.file.FileUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.bishe.domain.User;
import com.ruoyi.bishe.service.IUserService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户管理Controller
 *
 * @author ruoyi
 * @date 2023-03-19
 */
@RestController
@RequestMapping("/bishe/user")
public class UserController extends BaseController
{
    @Autowired
    private IUserService userService;

    /**
     * 查询用户管理列表
     */
    @PreAuthorize("@ss.hasPermi('bishe:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(User user)
    {
        startPage();
        List<User> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    /**
     * 导出用户管理列表
     */
    @PreAuthorize("@ss.hasPermi('bishe:user:export')")
    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, User user)
    {
        List<User> list = userService.selectUserList(user);
        ExcelUtil<User> util = new ExcelUtil<User>(User.class);
        util.exportExcel(response, list, "用户管理数据");
    }

    /**
     * 获取用户管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('bishe:user:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Integer userId)
    {
        return success(userService.selectUserByUserId(userId));
    }
    @Autowired
    private IExcellentService excellentService;
    /**
     * 新增用户管理
     */
    @PreAuthorize("@ss.hasPermi('bishe:user:add')")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody User user)
    {
        /*BigDecimal a=new BigDecimal("50");
        BigDecimal b=new BigDecimal("100");
        BigDecimal c=new BigDecimal("50");
        BigDecimal d=new BigDecimal("0");
        System.out.println("a=50,b=100,a.compareTo(b)的输出为："+a.compareTo(b));
        System.out.println("a=50,c=500,a.compareTo(c)的输出为："+a.compareTo(c));
        System.out.println("a=50,d=0,a.compareTo(d)的输出为："+a.compareTo(d));*/
        return toAjax(/*userService.insertUser(user)*/1);
    }

    /**
     * 修改用户管理
     */
    @PreAuthorize("@ss.hasPermi('bishe:user:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody User user)
    {
        return toAjax(userService.updateUser(user));
    }

    /**
     * 删除用户管理
     */
    @PreAuthorize("@ss.hasPermi('bishe:user:remove')")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Integer[] userIds)
    {
        return toAjax(userService.deleteUserByUserIds(userIds));
    }

    private String uploadPath="d:/ruoyi/uploadPath/document";
    /**
     * 课程文件上传部分
     */

    @PostMapping("/upload")
    public AjaxResult upload(MultipartFile file, boolean updateSupport) throws Exception {
        AjaxResult ajax = AjaxResult.success();
         if (file.isEmpty()){
             return error("文件为空");
        }
        //文件名
        String fileName=file.getOriginalFilename();
        String  suffixName= fileName.substring(fileName.lastIndexOf("."));
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
        System.out.println("==========================>"+fileTempObj.exists());
        //使用文件名检测文件是否已经存在
        if (fileTempObj.exists()){
            return error("文件已经存在");
        }
        try{
              file.transferTo(fileTempObj);
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
    @PostMapping("/download")
    public AjaxResult download(HttpServletResponse response, Integer courseId) throws Exception {
        String fileName="质量.docx";
        System.out.println(courseId);
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
