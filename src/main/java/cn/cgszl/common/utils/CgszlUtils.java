package cn.cgszl.common.utils;

import cn.cgszl.admin.controller.FileController;
import cn.cgszl.common.constant.WebConst;
import cn.cgszl.common.dao.pojo.User;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * 系统工具类
 *
 * @author cguisheng 2018/2/2 14:23
 */
public class CgszlUtils {

    /**
     * 获取当前登录用户
     *
     * @param request 请求对象
     * @return 当前用户信息
     */
    public static User getLoginUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session == null) {
            return null;
        }
        return (User) session.getAttribute(WebConst.LOGIN_SESSION_KEY);

    }

    /**
     * 判断文件是否是图片类型
     *
     * @param imageFile 文件输入流
     * @return 是否
     */
    public static boolean isImage(InputStream imageFile) {
        try {
            Image img = ImageIO.read(imageFile);
            if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getFileKey(String name) {
        String prefix = "/upload/" + DateKit.dateFormat(new Date(), "yyyy/MM");
        if (!new File(FileController.CLASSPATH + prefix).exists()) {
            new File(FileController.CLASSPATH + prefix).mkdirs();
        }

        name = StringUtils.trimToNull(name);
        if (name == null) {
            return prefix + "/" + UUID.UU32() + "." + null;
        } else {
            name = name.replace('\\', '/');
            name = name.substring(name.lastIndexOf("/") + 1);
            int index = name.lastIndexOf(".");
            String ext = null;
            if (index >= 0) {
                ext = StringUtils.trimToNull(name.substring(index + 1));
            }
            return prefix + "/" + UUID.UU32() + "." + (ext == null ? null : (ext));
        }
    }

    /**
     * 获取项目webapp路径
     *
     * @param request 请求
     * @return 路径
     */
    public static String getWebappPath(HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("");
        System.out.println("上传文件保存根路径：" + path);
        return path;
    }

    /**
     * 获取保存文件的位置,jar所在目录的路径
     *
     * @return
     */
    public static String getUplodFilePath() {
        String path = CgszlUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        path = path.substring(1, path.length());
        try {
            path = java.net.URLDecoder.decode(path, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int lastIndex = path.lastIndexOf("/") + 1;
        path = path.substring(0, lastIndex);
        File file = new File("");
        return file.getAbsolutePath() + "/";
    }

    /**
     * 从发的request请求的头信息里获取客户端IP地址
     *
     * @param request
     * @return ip 客户端IP地址
     */

    public static String getClientIPAddress(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        /*
         * 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割 "***.***.***.***".length() = 15
         */
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }

        return ip;
    }

    /**
     * 获取本地机器IP地址
     *
     * @return 本地IP地址
     */
    public static String getLocalIPAddress() {

        InetAddress inet = null;

        try {
            inet = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return inet.getHostAddress();
    }
}
