import api.WechatRobotApi;
import common.SendResult;
import config.WebhookConfig;
import message.TextMessage;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TextMessageTest
 * @Description 文本类型消息测试类
 * @Author GengRui
 * @Date 2021/6/7 17:04
 */
public class TextMessageTest {
    /**
     * 普通文本消息
     */
    @Test
    public void testNormal() {
        TextMessage message = new TextMessage("Hello World!");
        SendResult result = WechatRobotApi.send(WebhookConfig.send_url, message);
        System.out.println(result);
    }

    /**
     * 艾特群内成员的文本消息
     */
    @Test
    public void testWithAt() {
        TextMessage message = new TextMessage("你好，我是耿锐的助手");
        List<String> mentionedMobileList = new ArrayList<String>();
        mentionedMobileList.add("13026212376");
        message.setMentionedMobileList(mentionedMobileList);
        SendResult result = WechatRobotApi.send(WebhookConfig.send_url, message);
        System.out.println(result);
    }

    /**
     * 艾特所有人的文本消息
     */
    @Test
    public void testWithAtAll() {
        TextMessage message = new TextMessage("大家好，我是耿锐的助手");
        message.setIsAtAll(true);
        SendResult result = WechatRobotApi.send(WebhookConfig.send_url, message);
        System.out.println(result);
    }

    /**
     * 艾特群内成员并且艾特所有人的文本消息
     */
    @Test
    public void testWithAtAndAtAll() {
        TextMessage message = new TextMessage("大家好，通报批评就是他刚才移除了我");
        List<String> mentionedMobileList = new ArrayList<String>();
        mentionedMobileList.add("13026212376");
        message.setMentionedMobileList(mentionedMobileList);
        message.setIsAtAll(true);
        SendResult result = WechatRobotApi.send(WebhookConfig.send_url, message);
        System.out.println(result);
    }
}
