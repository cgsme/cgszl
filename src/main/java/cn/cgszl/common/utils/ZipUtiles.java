package cn.cgszl.common.utils;

import org.apache.commons.lang3.time.StopWatch;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;


/**
 * ZIP文件压缩与解压
 *
 * @author yshaobo@linewell.com
 * @since 2015年3月25日
 */
public class ZipUtiles {

    /**
     * 压缩
     *
     * @param zipFileName  压缩产生的zip包文件名--带路径,如果为null或空则默认按文件名生产压缩文件名
     * @param relativePath 相对路径，默认为空
     * @param directory    文件或目录的绝对路径
     * @throws FileNotFoundException
     * @throws IOException
     * @author yayagepei
     * @date 2008-8-26
     */
    public static void zip(String zipFileName, String relativePath,
                           String directory) throws FileNotFoundException, IOException {
        String fileName = zipFileName;
        if (fileName == null || fileName.trim().equals("")) {
            File temp = new File(directory);
            if (temp.isDirectory()) {
                fileName = directory + ".zip";
            } else {
                if (directory.indexOf(".") > 0) {
                    fileName = directory.substring(0, directory
                            .lastIndexOf("."))
                            + "zip";
                } else {
                    fileName = directory + ".zip";
                }
            }
        }
        ZipOutputStream zos = new ZipOutputStream(
                new FileOutputStream(fileName));
        try {
            zip(zos, relativePath, directory);
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (null != zos) {
                zos.close();
            }
        }
    }

    /**
     * 压缩
     *
     * @param zos          压缩输出流
     * @param relativePath 相对路径
     * @param absolutPath  文件或文件夹绝对路径
     * @throws IOException
     * @author yayagepei
     * @date 2008-8-26
     */
    private static void zip(ZipOutputStream zos, String relativePath,
                            String absolutPath) throws IOException {
        File file = new File(absolutPath);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                File tempFile = files[i];
                if (tempFile.isDirectory()) {
                    String newRelativePath = relativePath + tempFile.getName()
                            + File.separator;
                    createZipNode(zos, newRelativePath);
                    zip(zos, newRelativePath, tempFile.getPath());
                } else {
                    zipFile(zos, tempFile, relativePath);
                }
            }
        } else {
            zipFile(zos, file, relativePath);
        }
    }

    /**
     * 压缩文件
     *
     * @param zos          压缩输出流
     * @param file         文件对象
     * @param relativePath 相对路径
     * @throws IOException
     * @author yayagepei
     * @date 2008-8-26
     */
    private static void zipFile(ZipOutputStream zos, File file,
                                String relativePath) throws IOException {
        ZipEntry entry = new ZipEntry(relativePath + file.getName());
        zos.putNextEntry(entry);
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            int BUFFERSIZE = 2 << 10;
            int length = 0;
            byte[] buffer = new byte[BUFFERSIZE];
            while ((length = is.read(buffer, 0, BUFFERSIZE)) >= 0) {
                zos.write(buffer, 0, length);
            }
            zos.flush();
            zos.closeEntry();
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (null != is) {
                is.close();
            }
        }
    }

    /**
     * 创建目录
     *
     * @param zos          zip输出流
     * @param relativePath 相对路径
     * @throws IOException
     * @author yayagepei
     * @date 2008-8-26
     */
    private static void createZipNode(ZipOutputStream zos, String relativePath)
            throws IOException {
        ZipEntry zipEntry = new ZipEntry(relativePath);
        zos.putNextEntry(zipEntry);
        zos.closeEntry();
    }

    /**
     * 解压文件
     *
     * @param zipFile       待解压文件
     * @param descDirectory 解压到的路径
     * @return
     * @throws IOException
     */
    public static boolean unZip(File zipFile, String descDirectory) throws Exception {
        System.out.println("解压开始！");
        StopWatch sw = new StopWatch();
        sw.start();

        ZipFile zf = new ZipFile(zipFile);
        for (Enumeration entries = zf.entries(); entries.hasMoreElements(); ) {
            ZipEntry zipEntry = (ZipEntry) entries.nextElement();
            String name = zipEntry.getName();
            /**
             * 创建文件夹
             */
            System.out.println("name:" + name);
            int pos = name.lastIndexOf("\\");
            if (pos != -1) {
                name = name.substring(0, pos);
                File folder = new File(descDirectory + File.separator + name);
                if (!folder.exists()) {
                    folder.mkdirs();
                }
            }

            /**
             * 解压文件
             */
            File file = new File(descDirectory + File.separator
                    + zipEntry.getName());
            System.out.println((descDirectory + File.separator + zipEntry.getName()));
            if (!file.isDirectory()) {
                if (!file.exists()) {
//	            		if(!file.getParentFile().exists()){
//	            			file.getParentFile().mkdirs();
//	            		}
                    if (file.getParent() != null && !new File(file.getParent()).exists()) {
                        new File(file.getParent()).mkdirs();
                    }
//	            		file.mkdirs();
                    file.createNewFile();
                }
                InputStream is = null;
                OutputStream os = null;
                try {
                    is = zf.getInputStream(zipEntry);
                    file.createNewFile();
                    os = new FileOutputStream(file);
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = is.read(buffer)) != -1) {
                        os.write(buffer, 0, len);
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                } finally {

                    if (null != is) {
                        is.close();
                    }
                    if (null != os) {
                        os.close();
                    }
                }
            } else {
                if (!file.exists()) {
                    if (file.getParent() != null && !new File(file.getParent()).exists()) {
                        new File(file.getParent()).mkdirs();
                    }
                    file.mkdirs();
                }
            }
        }
        //关闭压缩的文件
        zf.close();
        System.out.println("解压完成！共费时： " + sw.toString() + ";解压后的所在目录路径为："
                + descDirectory);
        return true;
    }
}
