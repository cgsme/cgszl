package cn.cgszl.admin.controller;

import cn.cgszl.admin.service.AttachService;
import cn.cgszl.common.CommonResult;
import cn.cgszl.common.constant.WebConst;
import cn.cgszl.common.dao.dto.Types;
import cn.cgszl.common.dao.pojo.Attach;
import cn.cgszl.common.dao.pojo.User;
import cn.cgszl.common.utils.CgszlUtils;
import org.apache.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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
    public String fileManager() {
        return "admin/filemanager";
    }

    /**
     * 文件上传
     *
     * @return
     */
    @RequestMapping(value = "/admin/fileUpload")
    public CommonResult fileUpload(HttpServletRequest request, @RequestParam MultipartFile[] files) {
        try {
            // 附件存储路径
            String tmpPath = request.getSession().getServletContext().getRealPath("/") + WebConst.ATTACH_FILE_PATH_DIR;
            // 保存失败文件
            List<String> errorFileNameList = null;
            for (MultipartFile file : files) {
                // 获取文件名
                String fileName = file.getOriginalFilename();
                if (file.getSize() <= WebConst.MAX_FILE_SIZE) {  // 文件大小合适
                    // 文件相对路径
                    String fkey = CgszlUtils.getFileKey(fileName);
                    File tmpFile = new File(CLASSPATH + fkey);
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
}
