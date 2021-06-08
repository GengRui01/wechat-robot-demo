import api.WechatRobotApi;
import common.SendResult;
import config.WebhookConfig;
import message.MarkdownMessage;
import org.junit.Test;
import utils.MarkdownColorEnum;
import utils.MarkdownUtils;

import java.util.ArrayList;

/**
 * @ClassName MarkdownMessageTest
 * @Description markdown类型消息测试类
 * @Author GengRui
 * @Date 2021/6/7 19:49
 */
public class MarkdownMessageTest {
    /**
     * 普通markdown消息
     */
    @Test
    public void testNormal() {
        MarkdownMessage message = new MarkdownMessage();
        message.add("普通MD消息");
        SendResult result = WechatRobotApi.send(WebhookConfig.send_url, message);
        System.out.println(result);
    }

    /**
     * 带有标题的markdown消息
     */
    @Test
    public void testHeader() {
        MarkdownMessage message = new MarkdownMessage();

        message.add("普通MD消息");
        message.add(MarkdownUtils.getHeaderText(1, "一级标题"));
        message.add(MarkdownUtils.getHeaderText(2, "二级标题"));
        message.add(MarkdownUtils.getHeaderText(3, "三级标题"));
        message.add(MarkdownUtils.getHeaderText(4, "四级标题"));
        message.add(MarkdownUtils.getHeaderText(5, "五级标题"));
        message.add(MarkdownUtils.getHeaderText(6, "六级标题"));

        SendResult result = WechatRobotApi.send(WebhookConfig.send_url, message);
        System.out.println(result);
    }

    /**
     * 带有字体颜色（只支持3种内置颜色）的markdown消息
     */
    @Test
    public void testColour() {
        MarkdownMessage message = new MarkdownMessage();

        message.add(MarkdownUtils.getColorText(MarkdownColorEnum.orange, "橙色内容"));
        message.add(MarkdownUtils.getColorText(MarkdownColorEnum.gray, "灰色内容"));
        message.add(MarkdownUtils.getColorText(MarkdownColorEnum.green, "绿色内容"));

        SendResult result = WechatRobotApi.send(WebhookConfig.send_url, message);
        System.out.println(result);
    }

    /**
     * 带有有序、无序列表的markdown消息
     */
    @Test
    public void testList() {
        MarkdownMessage message = new MarkdownMessage();

        ArrayList<String> orderList = new ArrayList<String>();
        orderList.add("有序列表1");
        orderList.add("有序列表2");
        message.add(MarkdownUtils.getOrderListText(orderList));

        ArrayList<String> unOrderList = new ArrayList<String>();
        unOrderList.add("无序列表1");
        unOrderList.add("无序列表2");
        message.add(MarkdownUtils.getUnOrderListText(unOrderList));

        SendResult result = WechatRobotApi.send(WebhookConfig.send_url, message);
        System.out.println(result);
    }

    /**
     * 带有其他格式的markdown消息
     */
    @Test
    public void testOthers() {
        MarkdownMessage message = new MarkdownMessage();

        message.add(MarkdownUtils.getBoldText("加粗内容"));
        message.add(MarkdownUtils.getLinkText("链接内容", "https://www.gengruiblog.cn/"));
        message.add(MarkdownUtils.getCodeText("代码内容"));
        message.add(MarkdownUtils.getQuoteText("引用内容"));
        message.add(MarkdownUtils.getItalicText("斜体内容"));

        SendResult result = WechatRobotApi.send(WebhookConfig.send_url, message);
        System.out.println(result);
    }
}
