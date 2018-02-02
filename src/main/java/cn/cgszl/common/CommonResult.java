package cn.cgszl.common;

/**
 * 通用返回结果
 *
 * @author cguisheng 2017/12/27 14:37
 */
public class CommonResult {

    private static final long serialVersionUID = -556009412227715921L;
    private boolean success;    // 操作是否成功
    private Object data;     // 返回的结果
    private String message;     // 提示信息
    private int code;        // 状态码

    public CommonResult() {}

    public void init(String message, Object data) {
        this.success = true;
        this.message = message;
        this.data = data;
    }

    public void init(boolean paramBoolean, String message) {
        this.success = paramBoolean;
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean paramBoolean) {
        this.success = paramBoolean;
    }

}
