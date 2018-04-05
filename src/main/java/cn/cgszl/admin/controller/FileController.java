package cn.cgszl.admin.controller;

import cn.cgszl.common.service.AttachService;
import cn.cgszl.common.dao.dto.CommonResult;
import cn.cgszl.common.constant.WebConst;
import cn.cgszl.common.dao.dto.Types;
import cn.cgszl.common.dao.pojo.Attach;
import cn.cgszl.common.dao.pojo.User;
import cn.cgszl.common.exception.CgszlException;
import cn.cgszl.common.utils.CgszlUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件管理控制器
 *
 * @author cguisheng 2018/3/21 22:12
 */
@Controller
public class FileController {

    // 路径
    public static final String CLASSPATH = CgszlUtils.getUplodFilePath();

    // 附件管理业务接口
    @Resource
    private AttachService attachService;

    /**
     * 文件管理页面
     *
     * @return
     */
    @RequestMapping(value = "/admin/filemanager")
    public String fileManager(HttpServletRequest request, Integer page, Integer limit) {
        try {
            PageInfo<Attach> pageInfo = attachService.listAttach(page, limit);
            request.setAttribute("attachs", pageInfo.getList());
        } catch (CgszlException e) {
            request.setAttribute("errorMsg", "系统异常");
            e.printStackTrace();
        }
        return "admin/filemanager";
    }

    /**
     * 文件上传
     *
     * @return
     */
    @RequestMapping(value = "/admin/fileUpload")
    @ResponseBody
    public CommonResult fileUpload(HttpServletRequest request, @RequestParam MultipartFile[] files) {
        try {
            // 保存失败文件
            List<String> errorFileNameList = null;
            for (MultipartFile file : files) {
                // 获取文件名
                String fileName = file.getOriginalFilename();
                if (file.getSize() <= WebConst.MAX_FILE_SIZE) {  // 文件大小合适
                    // 文件相对路径
                    String fkey = CgszlUtils.getFileKey(fileName);
                    String servletContext = CgszlUtils.getWebappPath(request);
                    File tmpFile = new File(servletContext + fkey);
                    if (!tmpFile.exists()) {
                        tmpFile.mkdirs();
                    }
                    // 文件类型 图片为image，其他为file
                    String ftype = CgszlUtils.isImage(file.getInputStream()) ? Types.IMAGE.getType() : Types.FILE.getType();
                    // 获取当前用户
                    User loginUser = CgszlUtils.getLoginUser(request);
                    Integer userId = null;
                    if (loginUser != null) {
                        userId = loginUser.getUid();
                    }
                    // 保存文件
                    file.transferTo(tmpFile);
                    boolean result = attachService.sava(fileName, ftype, fkey, userId);
                    if (result) {
                        return CommonResult.ok();
                    }
                } else {  // 文件过大，上传失败
                    errorFileNameList = new ArrayList<String>();
                    // 保存上传失败的文件名
                    errorFileNameList.add(fileName);
                    CommonResult.fail(false, "上传失败", errorFileNameList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.fail(false, "文件上传失败");
        }
        return null;
    }

    /**
     * 根据附件标识删除附件
     *
     * @param id 附件标识
     * @return 通用结果对象
     */
    @RequestMapping(value = "/admin/attach/deleteById")
    @ResponseBody
    public CommonResult deleteById(HttpServletRequest request, Integer id) {
        try {
            Attach attach = attachService.getAttachById(id);
            boolean result = attachService.deleteById(id);
            if (result) {
                String webappPath = CgszlUtils.getWebappPath(request);
                File attachFile = new File(webappPath + attach.getFkey());
                if (attachFile.exists()) {
                    attachFile.delete();
                }
                return CommonResult.ok();
            }
            return CommonResult.fail(false, "删除失败");
        } catch (CgszlException e) {
            return CommonResult.fail(false, "系统异常");
        }
    }
}
