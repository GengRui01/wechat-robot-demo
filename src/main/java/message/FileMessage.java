package message;

import com.alibaba.fastjson.JSON;
import common.MsgtypeEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName FileMessage
 * @Description 文件类型消息类
 * @Author GengRui
 * @Date 2021/6/8 15:40
 */
public class FileMessage implements Message {
    /**
     * 通过文件上传接口获取的文件id 三天内有效
     */
    private String media_id;

    public FileMessage(String media_id) {
        this.media_id = media_id;
    }

    /**
     * 返回消息的Json格式字符串
     *
     * @return 消息的Json格式字符串
     */
    @Override
    public String toJson() throws IllegalArgumentException {
        Map<String, Object> items = new HashMap<String, Object>();
        items.put("msgtype", MsgtypeEnum.file.getValue());

        Map<String, Object> textContent = new HashMap<String, Object>();
        if (media_id.isEmpty()) {
            throw new IllegalArgumentException("通过文件上传接口获取的文件id不可为空");
        }
        textContent.put("media_id", media_id);
        items.put("file", textContent);
        return JSON.toJSONString(items);
    }
}
