package common;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName SendResult
 * @Description 消息发送结果类
 * @Author GengRui
 * @Date 2021/6/7 14:15
 */
public class SendResult {
    /**
     * 是否请求成功
     */
    private boolean isSuccess;
    /**
     * 请求错误编码
     */
    private String errorCode;
    /**
     * 请求错误信息
     */
    private String errorMsg;

    @Override
    public String toString() {
        Map<String, Object> items = new HashMap<String, Object>();
        items.put("errorCode", errorCode);
        items.put("errorMsg", errorMsg);
        items.put("isSuccess", isSuccess);
        return JSON.toJSONString(items);
    }

    public SendResult(boolean isSuccess, String errorCode, String errorMsg) {
        this.isSuccess = isSuccess;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
