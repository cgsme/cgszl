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
    private String code;        // 状态码
    private Integer count;       // 总记录数
//    private int type;
//    private ExceptionType exceptionType;
//    private String[] params;
//    private DataType dataType = DataType.CCIP_RESULT;

    public CommonResult() {}

    public void init(String paramString, Object data) {
        this.success = true;
        this.message = paramString;
        this.data = data;
    }

    public void init(boolean paramBoolean, String paramString) {
        this.success = paramBoolean;
        this.message = paramString;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String paramString) {
        this.message = paramString;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean paramBoolean) {
        this.success = paramBoolean;
    }

}
