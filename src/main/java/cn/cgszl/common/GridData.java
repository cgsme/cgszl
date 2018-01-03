package cn.cgszl.common;

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
    private String code;
    // 提示信息
    private String msg;
    // 总记录数
    private long count;
    // 要显示的数据的集合
    private List data;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
