package message;

import com.alibaba.fastjson.JSON;
import common.MsgtypeEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ImageMessage
 * @Description 图片类型消息类
 * @Author GengRui
 * @Date 2021/6/8 8:38
 */
public class ImageMessage implements Message {
    /**
     * 图片内容的base64编码
     */
    private String base64;
    /**
     * 图片内容（base64编码前）的md5值
     */
    private String md5;

    public ImageMessage(String base64, String md5) {
        this.base64 = base64;
        this.md5 = md5;
    }

    /**
     * 返回消息的Json格式字符串
     *
     * @return 消息的Json格式字符串
     */
    @Override
    public String toJson() throws IllegalArgumentException {
        Map<String, Object> items = new HashMap<String, Object>();
        items.put("msgtype", MsgtypeEnum.image.getValue());

        Map<String, Object> textContent = new HashMap<String, Object>();
        if (base64.isEmpty()) {
            throw new IllegalArgumentException("base64编码不可为空");
        }
        textContent.put("base64", base64);
        textContent.put("md5", md5);
        items.put("image", textContent);
        return JSON.toJSONString(items);
    }
}
