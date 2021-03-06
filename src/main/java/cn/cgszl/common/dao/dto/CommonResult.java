package cn.cgszl.common.dao.dto;

/**
 * 通用返回结果
 *
 * @author cguisheng 2017/12/27 14:37
 */
public class CommonResult {

    private boolean success;    // 操作是否成功
    private Object data;     // 返回的结果
    private String message;     // 提示信息

    private CommonResult() {
    }

    private CommonResult(boolean success) {
        this.success = success;
    }

    private CommonResult(boolean success, Object data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    private CommonResult(boolean isSuccess, Object data) {
        this.success = isSuccess;
        this.data = data;
    }

    private CommonResult(boolean success, Object o, String message, Object data) {
    }

    /**
     * 操作成功
     */
    public static CommonResult ok() {
        return new CommonResult(true);
    }

    /**
     * 操作成功
     */
    public static CommonResult ok(Object data) {
        return new CommonResult(true, data);
    }

    /**
     * 操作成功
     *
     * @param message 提示信息
     * @param data    返回数据
     */
    public static CommonResult ok(String message, Object data) {
        return new CommonResult(true, data, message);
    }

    public static CommonResult ok(boolean isSuccess, Object data) {
        return new CommonResult(isSuccess, data);
    }

    /**
     * 操作失败
     *
     * @param success 操作结果
     * @param message 提示信息
     */
    public static CommonResult fail(boolean success, String message) {
        return new CommonResult(success, null, message);
    }

    /**
     * 操作失败
     *
     * @param success 操作结果
     * @param message 提示信息
     */
    public static CommonResult fail(boolean success, String message, Object data) {
        return new CommonResult(success, null, message, data);
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
