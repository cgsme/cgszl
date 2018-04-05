package cn.cgszl.common.dao.dto;

import java.io.Serializable;
import java.util.List;

/**
 * layui 数据表格通用对象
 *
 * @author cguisheng 2018/01/02 22:48
 */
// 对象要通过网络传输必须实现序列化接口
public class GridData implements Serializable {

    // 状态码
    private Integer code;
    // 提示信息
    private String msg;
    // 总记录数
    private long count;
    // 要显示的数据的集合
    private List data;

    /**
     * 构造请求成功时的结果
     *
     * @param data 返回的数据
     * @return GridData layui表格通用对象
     */
    public static GridData build(List data, long count) {
        return new GridData(data, count);
    }

    private GridData() {
    }

    private GridData(List data, long count) {
        this.data = data;
        this.msg = "ok";
        this.count = count;
        // layui 默认code为0
        this.code = 0;
    }

    private GridData(Integer code, String msg, long count, List data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
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


}
