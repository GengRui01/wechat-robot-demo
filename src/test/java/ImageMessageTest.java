import api.WechatRobotApi;
import common.SendResult;
import config.WebhookConfig;
import message.ImageMessage;
import org.junit.Test;
import utils.ImageUtils;

/**
 * @ClassName ImageMessageTest
 * @Description 图片类型消息测试类
 * @Author GengRui
 * @Date 2021/6/8 8:49
 */
public class ImageMessageTest {
    /**
     * 本地图片消息
     */
    @Test
    public void testLocal() {
        String urlLocal = "D:/Blog/avatar.png";
        ImageMessage message = ImageUtils.toMessageByLocal(urlLocal);
        SendResult result = WechatRobotApi.send(WebhookConfig.send_url, message);
        System.out.println(result);
    }

    /**
     * 线上图片消息
     */
    @Test
    public void testOnline() throws Exception {
        String urlOnLine = "https://www.gengruiblog.cn/favicon.png";
        ImageMessage message = ImageUtils.toMessageByOnline(urlOnLine);
        SendResult result = WechatRobotApi.send(WebhookConfig.send_url, message);
        System.out.println(result);
    }
}
