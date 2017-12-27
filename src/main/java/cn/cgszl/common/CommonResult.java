package cn.cgszl.common;

/**
 * 通用返回结果
 *
 * @author cguisheng 2017/12/27 14:37
 */
public class CommonResult {

    private static final long serialVersionUID = -556009412227715921L;
    private boolean success;    // 操作是否成功
    private Object content;     // 返回的结果
    private String message;     // 提示信息
//    private int type;
//    private ExceptionType exceptionType;
//    private String[] params;
//    private DataType dataType = DataType.CCIP_RESULT;

    public CommonResult() {
    }

    public void init(String paramString, Object content) {
        this.success = true;
        this.message = paramString;
        this.content = content;
    }

    public void init(boolean paramBoolean, String paramString) {
        this.success = paramBoolean;
        this.message = paramString;
    }

//    public DataType getDataType()
//    {
//        return this.dataType;
//    }
//
//    public void setDataType(DataType paramDataType)
//    {
//        this.dataType = paramDataType;
//    }

    public Object getContent() {
        return this.content;
    }

    public void setContent(Object paramObject) {
        this.content = paramObject;
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

//    public int getType()
//    {
//        return this.type;
//    }
//
//    public void setType(int paramInt)
//    {
//        this.type = paramInt;
//    }

//    public ExceptionType getExceptionType()
//    {
//        return this.exceptionType;
//    }
//
//    public void setExceptionType(ExceptionType paramExceptionType)
//    {
//        this.exceptionType = paramExceptionType;
//    }

//    public String[] getParams()
//    {
//        return this.params;
//    }
//
//    public void setParams(String... paramVarArgs)
//    {
//        this.params = paramVarArgs;
//    }
}
