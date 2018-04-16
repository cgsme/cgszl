package cn.cgszl.common.dao.dto;

import java.util.List;

/**
 * layui文件上传响应对象
 *
 * @author cguisheng 2018/4/16 14:29
 */
public class FileUploadResult {

    private Integer code;
    private String msg;
    private Object data;

    /**
     * 操作成功
     *
     * @param msg  提示信息
     * @param date 响应数据
     * @return
     */
    public static FileUploadResult ok(String msg, Object date) {
        return new FileUploadResult(msg, date);
    }

    /**
     * 操作失败
     *
     * @param msg 提示信息
     * @return
     */
    public static FileUploadResult fail(String msg) {
        return new FileUploadResult(msg);
    }

    private FileUploadResult(String msg) {
        this.code = 1;
        this.msg = msg;
        this.data = null;
    }

    private FileUploadResult(String msg, Object date) {
        this.code = 0; // layui默认0为成功
        this.msg = msg;
        this.data = date;
    }

    private FileUploadResult() {
    }

    private FileUploadResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
