package message;

import com.alibaba.fastjson.JSON;
import common.MsgtypeEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MarkdownMessage
 * @Description markdown类型消息类
 * @Author GengRui
 * @Date 2021/6/7 19:07
 */
public class MarkdownMessage implements Message {
    /**
     * markdown元素列表 每一行内容为一个元素
     */
    private List<String> items = new ArrayList<String>();

    public void add(String text) {
        items.add(text);
    }

    /**
     * 返回消息的Json格式字符串
     *
     * @return 消息的Json格式字符串
     */
    @Override
    public String toJson() throws IllegalArgumentException {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("msgtype", MsgtypeEnum.markdown.getValue());

        if (items.isEmpty()) {
            throw new IllegalArgumentException("markdown内容不可为空");
        }
        StringBuffer markdownText = new StringBuffer();
        for (String item : items) {
            markdownText.append(item + "\n");
        }

        Map<String, Object> markdown = new HashMap<String, Object>();
        markdown.put("content", markdownText.toString());
        result.put("markdown", markdown);

        return JSON.toJSONString(result);
    }
}
