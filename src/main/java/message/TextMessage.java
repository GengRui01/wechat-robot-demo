package message;

import com.alibaba.fastjson.JSON;
import common.MsgtypeEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TextMessage
 * @Description 文本类型消息类
 * @Author GengRui
 * @Date 2021/6/7 16:23
 */
public class TextMessage implements Message {
    /**
     * 文本内容
     */
    private String text;
    /**
     * 是否@所有人
     */
    private boolean isAtAll;
    /**
     * 需要@的群成员手机号
     */
    private List<String> mentionedMobileList;

    /**
     * 返回消息的Json格式字符串
     *
     * @return 消息的Json格式字符串
     */
    @Override
    public String toJson() throws IllegalArgumentException {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("msgtype", MsgtypeEnum.text.getValue());

        Map<String, Object> textContent = new HashMap<String, Object>();
        if (text.isEmpty()) {
            throw new IllegalArgumentException("文本内容不可为空");
        }
        textContent.put("content", text);
        if (isAtAll) {
            if (mentionedMobileList == null)
                mentionedMobileList = new ArrayList<String>();
            mentionedMobileList.add("@all");
        }
        if (mentionedMobileList != null && !mentionedMobileList.isEmpty()) {
            textContent.put("mentioned_mobile_list", mentionedMobileList);
        }
        result.put("text", textContent);
        return JSON.toJSONString(result);
    }

    public TextMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getIsAtAll() {
        return isAtAll;
    }

    public void setIsAtAll(boolean isAtAll) {
        this.isAtAll = isAtAll;
    }

    public List<String> getMentionedMobileList() {
        return mentionedMobileList;
    }

    public void setMentionedMobileList(List<String> mentionedMobileList) {
        this.mentionedMobileList = mentionedMobileList;
    }
}
