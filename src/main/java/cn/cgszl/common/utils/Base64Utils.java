package cn.cgszl.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

/**
 * Base64工具类
 *
 * @author cshiyong
 * 2014-9-19
 */
public class Base64Utils {

    /**
     * 将二进制流转换为Base64编码流
     *
     * @param in 二进制流
     * @return Base64编码流
     */
    public static byte[] encode(byte[] in) {
        return Base64.encodeBase64(in);
    }

    public static String encode(String s) {
        return new String(encode(s.getBytes()));
    }

    /**
     * 将Base64转换为二进制流
     *
     * @param in Base64编码流
     * @return 二进制流
     */
    public static byte[] decode(byte[] in) {
        return Base64.decodeBase64(in);
    }

    public static String decode(String s) {
        return new String(decode(s.getBytes()));
    }

    /**
     * 将文件转换为Base64编码
     *
     * @param file 要转换文件
     * @return 转换后Base64编码
     */
    public static String encode(File file) {

        if (null == file) {
            return "";
        }

        InputStream in = null;
        String out = new String();

        try {
            in = new FileInputStream(file);

            // 读取缓冲区
            byte[] readBuf = new byte[(int) file.length()];

            if (in.read(readBuf) > 0) {
                out = new String(Base64Utils.encode(readBuf));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return out;
    }

    /**
     * 将Base64编码转换为文件
     *
     * @param code Base64编码
     * @return 文件
     */
    public static File decodeFile(String code) {

        if (StringUtils.isEmpty(code)) {
            return null;
        }

        OutputStream out = null;
        File file = null;
        try {

            file = File.createTempFile("temp", ".temp");
            out = new FileOutputStream(file);

            // 读取缓冲区
            byte[] readBuf = Base64.decodeBase64(code.getBytes());

            out.write(readBuf);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;

    }
}
