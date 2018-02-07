package cn.cgszl.common;

/**
 * 通用返回结果
 *
 * @author cguisheng 2017/12/27 14:37
 */
public class CommonResult {

    private boolean success;    // 操作是否成功
    private Object data;     // 返回的结果
    private String message;     // 提示信息

    public CommonResult() {}

    public CommonResult(boolean success) {
        this.success = success;
    }

    public CommonResult(boolean success, Object data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    /**
     * 操作成功
     */
    public static CommonResult ok() {
        return new CommonResult(true);
    }

    /**
     * 操作成功
     * @param message 提示信息
     * @param data 返回数据
     */
    public static CommonResult ok(String message, Object data) {
        return new CommonResult(true, data, message);
    }

    /**
     * 操作失败
     * @param success 操作结果
     * @param message 提示信息
     */
    public static CommonResult fail(boolean success, String message) {
        return new CommonResult(success, null, message);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
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
