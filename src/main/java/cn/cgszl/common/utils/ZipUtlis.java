package cn.cgszl.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


/**
 * ZIP文件压缩与解压
 *
 * @author cguisheng 2018/03/05
 */
public class ZipUtlis {

    /**
     * 压缩文件夹
     *
     * @param srcFolder   要压缩的文件
     * @param destZipFile 保存的文件路径
     * @throws Exception
     */
    public static void zipFolder(String srcFolder, String destZipFile) throws Exception {
        ZipOutputStream zip = null;
        FileOutputStream fileOutputStream = null;
        fileOutputStream = new FileOutputStream(destZipFile);
        zip = new ZipOutputStream(fileOutputStream);
        addFolderToZip("", srcFolder, zip);
        zip.flush();
        zip.close();
    }

    /**
     * 压缩文件
     *
     * @param filePath 要压缩的文件路径
     * @param zipPath  压缩完成的文件路径
     * @throws Exception
     */
    public static void zipFile(String filePath, String zipPath) throws Exception {
        byte[] buffer = new byte[1024];
        FileOutputStream fos = new FileOutputStream(zipPath);
        ZipOutputStream zos = new ZipOutputStream(fos);
        ZipEntry ze = new ZipEntry("spy.log");
        zos.putNextEntry(ze);
        FileInputStream in = new FileInputStream(filePath);
        int len;
        while ((len = in.read(buffer)) > 0) {
            zos.write(buffer, 0, len);
        }
        in.close();
        zos.closeEntry();
        //remember close it
        zos.close();
    }

    /**
     * 添加文件到压缩包
     *
     * @param path
     * @param srcFile
     * @param zip
     * @throws Exception
     */
    public static void addFileToZip(String path, String srcFile, ZipOutputStream zip)
            throws Exception {

        File folder = new File(srcFile);
        if (folder.isDirectory()) {
            addFolderToZip(path, srcFile, zip);
        } else {
            byte[] buf = new byte[1024];
            int len;
            FileInputStream in = new FileInputStream(srcFile);
            zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
            while ((len = in.read(buf)) > 0) {
                zip.write(buf, 0, len);
            }
        }
    }

    /**
     * 添加文件到压缩包
     *
     * @param path      文件夹路径
     * @param srcFolder 源目录
     * @param zip       压缩文件流
     * @throws Exception
     */
    public static void addFolderToZip(String path, String srcFolder, ZipOutputStream zip) throws Exception {
        File folder = new File(srcFolder);
        if (null != path && folder.isDirectory()) {
            for (String fileName : folder.list()) {
                if (path.equals("")) {
                    addFileToZip(folder.getName(), srcFolder + "/" + fileName, zip);
                } else {
                    addFileToZip(path + "/" + folder.getName(), srcFolder + "/" + fileName, zip);
                }
            }
        }
    }
}
