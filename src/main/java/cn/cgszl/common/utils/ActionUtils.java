package cn.cgszl.common.utils;

import cn.cgszl.common.CommonResult;

/**
 * 交互的工具类
 *
 * @author cguisheng
 * @since 2017-12-27
 */
public class ActionUtils {

    /**
     * 获取交互的结果
     *
     * @param isSuccess 是否成功
     * @param message   信息
     * @param content   内容
     * @return 交互的结果
     */
    public static CommonResult getActionResult(boolean isSuccess,
                                               String message, Object content) {
        CommonResult ccipResult = new CommonResult();
        ccipResult.setSuccess(isSuccess);
        ccipResult.setMessage(message);
        ccipResult.setData(content);
//		CommonResult ar = new CommonResult();
        return ccipResult;
    }
}
