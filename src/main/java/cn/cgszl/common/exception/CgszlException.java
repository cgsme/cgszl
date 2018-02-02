package cn.cgszl.common.exception;

/**
 * 自定义异常
 *
 * @author cguisheng 2018/1/3 21:35
 */
public class CgszlException extends RuntimeException {
    public CgszlException() {
    }

    public CgszlException(String message) {
        super(message);
    }

    public CgszlException(String message, Throwable cause) {
        super(message, cause);
    }

    public CgszlException(Throwable cause) {
        super(cause);
    }

}
