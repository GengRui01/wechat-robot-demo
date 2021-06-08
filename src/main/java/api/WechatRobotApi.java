package api;

import com.alibaba.fastjson.JSONObject;
import common.SendResult;
import message.Message;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @ClassName WechatRobotApi
 * @Description 发送请求类
 * @Author GengRui
 * @Date 2021/6/7 14:44
 */
public class WechatRobotApi {
    /**
     * 发送消息
     *
     * @param sendUrl
     * @param message
     * @return
     */
    public static SendResult send(String sendUrl, Message message) {
        // 判断sendUrl是否为空
        if (sendUrl.isEmpty()) {
            return new SendResult(false, "10000", "webhook地址不可为空");
        }
        try {
            // 发送post请求
            HttpResponse response = post(sendUrl, message.toJson());
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                String errorCode = "10" + response.getStatusLine().getStatusCode();
                return new SendResult(false, errorCode, "Http请求错误 错误编码为后三位");
            } else {
                String result = EntityUtils.toString(response.getEntity());
                JSONObject obj = JSONObject.parseObject(result);
                String errorCode = obj.getString("errcode");
                return new SendResult(errorCode.equals("0"), errorCode, obj.getString("errmsg"));
            }
        } catch (IllegalArgumentException e) {
            return new SendResult(false, "10001", e.getMessage());
        } catch (IOException e) {
            return new SendResult(false, "10002", "Http请求IO异常");
        }
    }

    /**
     * 发送post请求
     *
     * @param sendUrl
     * @param json
     * @return
     * @throws IOException
     */
    private static HttpResponse post(String sendUrl, String json) throws IOException {
        HttpPost httppost = new HttpPost(sendUrl);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        StringEntity se = new StringEntity(json, "utf-8");
        httppost.setEntity(se);
        HttpResponse response = HttpClients.createDefault().execute(httppost);
        return response;
    }
}
