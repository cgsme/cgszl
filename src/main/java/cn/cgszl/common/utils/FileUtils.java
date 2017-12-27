package cn.cgszl.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

/**
 * 文件工具类
 *
 * @author
 * @since
 */
public class FileUtils {

    //Log日志登记
    private static final Log LOG = LogFactory.getLog(FileUtils.class);

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();//递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除      
        return dir.delete();
    }

    /**
     * 获取文件的文本内容
     *
     * @param filePath 文件的路径
     * @param charset  文件的编码
     * @return 文件包含的文本的内容
     */
    public static String getFileStr(String filePath, String charset) {
        String fileStr = "";
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(filePath);
            fileStr = getFileStrByStream(fin, charset);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LOG.error(e);
        } finally {
            if (null != fin) {
                try {
                    fin.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return fileStr;
    }

    /**
     * 获取文件的文本内容通过文件流
     *
     * @param charset 文件的编码
     * @return 文件包含的文本的内容
     */
    public static String getFileStrByStream(InputStream fin, String charset) {
        String fileStr = "";
        InputStreamReader fileIn = null;
        BufferedReader infm = null;

        try {
            fileIn = new InputStreamReader(fin, charset);
            infm = new BufferedReader(fileIn);
            String s = "";
            while (true) {
                s = infm.readLine();
                if (s == null)
                    break;
                if (StringUtils.isNotEmpty(fileStr)) {
                    fileStr += "\n";
                }
                fileStr += s;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LOG.error(e);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            LOG.error(e);
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error(e);
        } finally {
            //2012-03-08 add by xhuatang@linewell.com
            //关闭文件流
            try {
                infm.close();
                fileIn.close();
                fin.close();
            } catch (IOException e) {
                e.printStackTrace();
                LOG.error(e);
            }
        }
        return fileStr;
    }

    /**
     * 根据指定的编码将内容写入到相应的文件地址中
     *
     * @param fileName 文件地址 String
     * @param data     写入数据 String
     * @param encoding 编码类型 String
     */
    public static void writeStringToFile(String fileName, String data, String encoding) {
        File file = new File(fileName);
        writeStringToFile(file, data, encoding);
    }

    /**
     * 根据指定的编码将内容写入到相应的文件地址中
     *
     * @param file     文件对象 File
     * @param data     写入数据 String
     * @param encoding 编码类型 String
     */
    public static void writeStringToFile(File file, String data, String encoding) {
        writeStringToFile(file, data, encoding, false);
    }

    /**
     * 根据指定的编码将内容写入到相应的文件地址中
     *
     * @param file     文件对象 File
     * @param data     写入数据 String
     * @param encoding 编码类型 String
     * @param append   是否追加 boolean
     */
    public static void writeStringToFile(File file, String data, String encoding, boolean append) {
        OutputStream out = null;
        try {
            out = openOutputStream(file, append);
            write(data + "\n\r", out, encoding);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(out);
        }
    }

    /**
     * 根据指定的编码将内容写入到相应的文件地址中
     *
     * @param in  文件流对象
     * @param out 输出流对象
     */
    public static void write(InputStream in, OutputStream out) {
        try {
            byte[] buffer = new byte[5000];
            int len = 0;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(out);
        }
    }

    /**
     * 根据指定的编码将内容输出到流中
     *
     * @param data     数据 String
     * @param output   输出流 OutputStream
     * @param encoding 编码类型 String
     */
    private static void write(String data, OutputStream output, String encoding) throws IOException {
        if (data != null) {
            if (encoding == null) {
                write(data, output);
            } else {
                output.write(data.getBytes(encoding));
            }
        }
    }

    /**
     * 根据指定的编码将内容输出到流中
     *
     * @param data   数据 String
     * @param output 输出流 OutputStream
     */
    private static void write(String data, OutputStream output) throws IOException {
        if (data != null) {
            output.write(data.getBytes());
        }
    }

    /**
     * 从文件对象获取相应的文件流
     *
     * @param file 文件对象 File
     * @return 文件流 FileOutputStream
     * @throws IOException IO异常
     */
    private static FileOutputStream openOutputStream(File file, boolean append) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            if (file.canWrite() == false) {
                throw new IOException("File '" + file + "' cannot be written to");
            }
        } else {
            File parent = file.getParentFile();
            if (parent != null && parent.exists() == false) {
                if (parent.mkdirs() == false) {
                    throw new IOException("File '" + file + "' could not be created");
                }
            }
        }
        return new FileOutputStream(file, append);
    }

    /**
     * 关闭输出流
     *
     * @param output 输出流 OutputStream
     */
    private static void closeQuietly(OutputStream output) {
        try {
            if (output != null) {
                output.close();
            }
        } catch (IOException ioe) {
        }
    }

    /**
     * 根据文件名，下载文件，以流文件输出到客户端
     *
     * @param fileName     文件名
     * @param filePathName 服务器 端的文件全路径
     * @param response     HttpServletResponse
     */
    public static void downFile(String fileName, String filePathName, HttpServletResponse response) {
        StreamFetcher sf = new StreamFetcher(filePathName, null);
        File downFile = new File(filePathName);
        if (!downFile.exists()) {
            System.out.println("找不到文件,请确定文件是否存在filePathName:" + filePathName);
            return;
        }
        InputStream is = null;
        try {
            is = new FileInputStream(filePathName);
            downFile(fileName, sf.getStreamContentType(), is, response);
        } catch (FileNotFoundException e) {
            LOG.error(e);
            return;
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 文件下载，文件流输出到客户端
     *
     * @param fileName 文件名
     * @param fileType 文件ContentType类型
     * @param is       文件流
     * @param response HttpServletResponse
     *                 Warning：该方法存在BUG，未区分IE和非IE浏览器编码的差异，故有时候在非IE浏览器下会出现导出的文件名为乱码的现象，
     *                 建议使用 downFile(String fileName, String fileType,
     *                 InputStream is, HttpServletResponse response, boolean isIE)
     */
    @Deprecated
    public static void downFile(String fileName, String fileType, InputStream is, HttpServletResponse response) {
        try {
            fileName = (fileName == null ? "未命名" : fileName);
            //fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            // modify by jly 2016-08-01 编码格式改为GB2312,解决附件ie下载时中文文件名乱码问题
            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            downFileWithEncodeFileName(fileName, fileType, is, response);
        } catch (UnsupportedEncodingException e) {
            LOG.error(e);
            return;
        }
    }

    /**
     * 文件下载，根据不同的浏览器，导出不同编码的文件流输出到客户端
     *
     * @param fileName 文件名
     * @param fileType 文件ContentType类型
     * @param is       文件流
     * @param response HttpServletResponse
     * @param isIE     是否为IE的boolean值
     */
    public static void downFile(String fileName, String fileType,
                                InputStream is, HttpServletResponse response, boolean isIE) {

        try {
            fileName = (fileName == null ? "未命名" : fileName);
            if (isIE) {
                // modify by jly 2016-08-01 编码格式改为GB2312,解决附件ie下载时中文文件名乱码问题
                fileName = new String(fileName.getBytes("GB2312"), "ISO-8859-1");
            } else {
                fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            }
            downFileWithEncodeFileName(fileName, fileType, is, response);
        } catch (UnsupportedEncodingException e) {
            LOG.error(e);
            e.printStackTrace();
            return;
        }
    }

    private static void downFileWithEncodeFileName(String fileName,
                                                   String fileType, InputStream is, HttpServletResponse response) {
        BufferedInputStream bs = new BufferedInputStream(is);
        if (bs != null) {
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            if (StringUtils.isEmpty(fileType)) {
                StreamFetcher sf = new StreamFetcher(fileName, null);
                fileType = sf.getStreamContentType();
            }
            response.setContentType(fileType);
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "max-age=0");
            OutputStream bos = null;
            try {
                bos = new BufferedOutputStream(response.getOutputStream());
            } catch (IOException e) {
                LOG.error(e);
                e.printStackTrace();
                return;
            }
            byte[] buff = new byte[2048];
            int bytesRead;
            try {
                while ((bytesRead = is.read(buff, 0, buff.length)) != -1) {
                    bos.write(buff, 0, bytesRead);
                }
                response.flushBuffer();
            } catch (Exception e) {
                LOG.error(e);
            } finally {
                if (null != bos)
                    try {
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                if (null != is)
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
    }

    /**
     * 更新和保存文件
     *
     * @param fileName 文件名
     * @param request  HttpServletRequest
     * @return 是否成功标识 true false
     */
    public static boolean updateFile(String fileName, HttpServletRequest request) {
        File file = new File(fileName);
        // 文件输出流对象
        FileOutputStream fo = null;
        BufferedInputStream is = null;
        try {
            fo = new FileOutputStream(file);
            is = new BufferedInputStream(request.getInputStream()); // 提交的内容
            byte[] bss = new byte[4096];
            int i = -1; // 每次读取的字节内容
            while ((i = is.read(bss, 0, 4096)) > 0) { // 一次读取4K字节写入
                fo.write(bss, 0, i);
            }
            fo.flush();

            return true;
        } catch (FileNotFoundException e) {
            System.out.println("找不到文件！" + e.getMessage());
            LOG.error(e);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("修改内容时出错！" + e.getMessage());
            LOG.error(e);
            e.printStackTrace();
        } finally {
            try {
                if (null != fo) {
                    fo.close();
                }
                if (null != is) {
                    is.close();
                }
            } catch (IOException e) {
                // TODO: handle exception
                e.printStackTrace();
            }

        }
        return false;
    }

    /**
     * 删除文件
     *
     * @param fileName 文件名
     * @return 删除成功与否 true false
     */
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
            return true;
        }
        return false;
    }

    /**
     * 判断文件是否存在
     *
     * @param fileName 文件路径包括文件名
     * @return 文件是否存在 true or flase
     */
    public static boolean fileIsExist(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            return true;
        }
        return false;
    }

    /**
     * 获取文件名，不包括路径
     *
     * @param fullName 文件路径包括文件名
     * @return 文件名
     */
    public static String getFileName(String fullName) {
        int pos = fullName.lastIndexOf('/');
        if (pos < 0) {
            pos = fullName.lastIndexOf('\\');
        }
        return (pos >= 0 ? fullName.substring(pos + 1) : fullName);
    }

    /**
     * 删除文件夹和下面的文件
     *
     * @param delpath 删除的目录
     * @return 删除成功与否标识 true false
     */
    public static boolean deleteDir(String delpath) {
        try {
            File file = new File(delpath);
            if (!file.isDirectory()) {
                file.delete();
            } else if (file.isDirectory()) {
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File delfile = new File(delpath + File.separator + filelist[i]);
                    if (!delfile.isDirectory()) {
                        delfile.delete();
                    } else if (delfile.isDirectory()) {
                        deleteDir(delpath + File.separator + filelist[i]);
                    }
                }
                file.delete();
            }
        } catch (Exception e) {
            LOG.error(e);
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 获取文件名的后缀()
     *
     * @param fileName
     * @return
     */
    public static String getFileNameExt(String fileName) {
        String ext = "";
        if (StringUtils.isNotEmpty(fileName.trim())) {
            int index = fileName.lastIndexOf(".");
            if (index > -1) {
                ext = fileName.substring(index);
            }
        }
        return ext;
    }

    /**
     * 获取图片显示流
     *
     * @param request     request
     * @param response    response
     * @param path        文件路径
     * @param contentType 文件流类型
     */
    public static void showPic(HttpServletRequest request, HttpServletResponse response, String path, String contentType) {
        if (StringUtils.isEmpty(path)) {
            return;
        }
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        ServletOutputStream out = null;
        InputStream is = null;
        try {//image/png
            out = response.getOutputStream();
            response.setContentType(contentType);
            is = new FileInputStream(file);
            int size = is.available();
            byte[] bzp = new byte[size];
            is.read(bzp);
            is.close();
            out.write(bzp);
            out.close();
        } catch (IOException e) {
            System.out.println("获取文件流失败！");
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    System.out.println("关闭文件流失败！");
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 复制文件夹
     *
     * @param sourceDir 原文件夹路径
     * @param targetDir 要拷贝的目标路径
     * @throws IOException
     */
    public static void copyDirectiory(String sourceDir, String targetDir) throws IOException {
        StopWatch sw = new StopWatch();
        sw.start();

        // 新建目标目录
        (new File(targetDir)).mkdirs();
        // 获取源文件夹当前下的文件或目录
        File[] file = (new File(sourceDir)).listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {
                // 源文件
                File sourceFile = file[i];
                // 目标文件
                File targetFile = new File(new File(targetDir).getAbsolutePath() + File.separator + file[i].getName());
                FileUtils.copyFile(sourceFile, targetFile);
            }
            if (file[i].isDirectory()) {
                // 准备复制的源文件夹
                String dir1 = sourceDir + "/" + file[i].getName();
                // 准备复制的目标文件夹
                String dir2 = targetDir + "/" + file[i].getName();
                copyDirectiory(dir1, dir2);
            }
        }
        sw.stop();
    }

    /**
     * 复制单个文件
     *
     * @param sourceFile 原文件路径
     * @param targetFile 目标文件路径
     * @throws IOException
     */
    public static void copyFile(File sourceFile, File targetFile) throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            // 新建文件输入流并对它进行缓冲
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

            // 新建文件输出流并对它进行缓冲
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
        } finally {
            // 关闭流
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
    }

    /**
     * 写入文件
     *
     * @param path     路径
     * @param content  文本内容
     * @param fileName 文件名
     */
    public static void writerText(String path, String content, String fileName) {

        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File dirFileName = new File(path + fileName);
        if (!dirFileName.exists()) {
            try {
                dirFileName.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(path + fileName, true), "UTF-8");
            BufferedWriter bw1 = new BufferedWriter(write);
            bw1.write(content);
            bw1.flush();
            bw1.close();
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 逐行读取
     *
     * @param filePath
     * @return
     */
    public static List<String> readFile(String filePath) {
        List<String> result = new ArrayList<String>();
        BufferedReader br = null;
        // add by jly 2016-09-07 修复导入模块会报文件不存在错误问题
        File file = new File(filePath);
        if (!file.exists()) {
            return result;
        }
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8")); // 建立BufferedReader对象，并实例化为br
            String Line = br.readLine();// 从文件读取一行字符串
            // 判断读取到的字符串是否不为空
            int i = 1;
            while (Line != null) {
                if (!"".equals(Line)) {
                    System.out.println("行内容为：" + i + "：：" + Line);
                    i = i + 1;
                    result.add(Line);
                }
                Line = br.readLine();// 从文件中继续读取一行数据
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();// 关闭BufferedReader对象
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 根据文件名，批量下载文件，以流文件输出到客户端
     *
     * @param map      文件名及文件内容集合，key为文件名，value对应文件内容
     * @param response HttpServletResponse
     */
    public static void bacthDownFile(Map<String, InputStream> map, HttpServletResponse response) {
        // 使用边压缩边下载文件的方式进行
        ZipOutputStream zos = null;
        // 获取输出流
        try {
            // 清空response
            response.reset();
            OutputStream out = response.getOutputStream();
            String fileName = "";
            // 获取文件名
            for (Entry<String, InputStream> entry : map.entrySet()) {
                fileName = entry.getKey();
                fileName = fileName.substring(0, fileName.indexOf("."));
                fileName += "等.zip";
                break;
            }
            fileName = new String(fileName.getBytes("GB2312"), "ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            //			response.setContentType("application/octet-stream");
            response.setContentType("application/zip");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "max-age=0");
            zos = new ZipOutputStream(out);
            zipFiles(map, zos);
            zos.setEncoding("GBK");
            zos.flush();
            response.flushBuffer();
        } catch (IOException e) {
            LOG.error(e);
            e.printStackTrace();
        } finally {
            if (null != zos) {
                try {
                    zos.close();
                } catch (IOException e) {
                    LOG.error(e);
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将InputStream放入ZipOutputStream
     *
     * @param zos
     * @param fileName
     * @param is
     */
    public static void zipFile(ZipOutputStream zos, String fileName, InputStream is) {
        try {

            // 文件名
            zos.putNextEntry(new ZipEntry(fileName));

            byte[] buffer = new byte[2048];
            if (null != is) {
                int bytesRead;
                while ((bytesRead = is.read(buffer, 0, buffer.length)) != -1) {
                    zos.write(buffer, 0, bytesRead);
                }
            }
            zos.setEncoding("GBK");
            zos.closeEntry();
        } catch (IOException e) {
            LOG.error(e);
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    LOG.error(e);
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @param map 文件名及文件内容集合，key为文件名，value对应文件内容
     * @param zos 压缩流
     */
    private static void zipFiles(Map<String, InputStream> map, ZipOutputStream zos) {
        byte[] buffer = new byte[2048];
        InputStream is = null;
        for (Entry<String, InputStream> entry : map.entrySet()) {
            try {
                // 文件名
                zos.putNextEntry(new ZipEntry(entry.getKey()));
                // 文件内容
                is = entry.getValue();
                if (null != is) {
                    int bytesRead;
                    while ((bytesRead = is.read(buffer, 0, buffer.length)) != -1) {
                        zos.write(buffer, 0, bytesRead);
                    }
                }
                zos.setEncoding("GBK");
                zos.closeEntry();
            } catch (IOException e) {
                LOG.error(e);
                e.printStackTrace();
            } finally {
                if (null != is) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        LOG.error(e);
                        e.printStackTrace();
                    }
                }
            }
        }
        if (null != map) {
            map = null;
        }
    }

}