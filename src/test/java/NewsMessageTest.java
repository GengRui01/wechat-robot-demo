import api.WechatRobotApi;
import common.SendResult;
import config.WebhookConfig;
import message.Article;
import message.NewsMessage;
import org.junit.Test;

/**
 * @ClassName NewsMessageTest
 * @Description 图文类型消息测试类
 * @Author GengRui
 * @Date 2021/6/8 9:36
 */
public class NewsMessageTest {
    /**
     * 一条图文消息
     */
    @Test
    public void testOneArticle() {
        NewsMessage message = new NewsMessage();
        message.addArticle(new Article("耿锐的博客", "欢迎的来到耿锐的技术小栈",
                "https://www.gengruiblog.cn/", "https://www.gengruiblog.cn/favicon.png"));
        SendResult result = WechatRobotApi.send(WebhookConfig.send_url, message);
        System.out.println(result);
    }

    /**
     * 多条图文消息
     */
    @Test
    public void testMoreArticle() {
        NewsMessage message = new NewsMessage();
        message.addArticle(new Article("长方形图", "耿锐的博客 - MVC设计模式",
                "https://www.gengruiblog.cn/article/20210224.html", "https://www.gengruiblog.cn/picture/mvc/2.png"));
        message.addArticle(new Article("方形图", "昆大圣博客 - 迷失的人迷失了，相逢的人会再相逢",
                "https://blog.ecutdl.cn/", "https://blog.ecutdl.cn/usr/uploads/2021/05/3275615662.png"));
        message.addArticle(new Article("无图", "百度一下你就知道",
                "https://www.baidu.com/", null));
        SendResult result = WechatRobotApi.send(WebhookConfig.send_url, message);
        System.out.println(result);
    }
}
