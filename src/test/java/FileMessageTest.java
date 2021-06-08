import api.WechatRobotApi;
import common.SendResult;
import config.WebhookConfig;
import message.FileMessage;
import org.junit.Test;
import utils.FileUtils;

/**
 * @ClassName FileMessageTest
 * @Description 文件类型消息测试类
 * @Author GengRui
 * @Date 2021/6/8 13:56
 */
public class FileMessageTest {
    /**
     * 普通文件消息
     */
    @Test
    public void testNormal() {
        String mediaId = FileUtils.upload(WebhookConfig.upload_media_url, "D:/Work/需求调研汇总.xlsx");
        FileMessage message = new FileMessage(mediaId);
        SendResult result = WechatRobotApi.send(WebhookConfig.send_url, message);
        System.out.println(result);
    }

    /**
     * 音频文件消息
     */
    @Test
    public void testMusic() {
        String mediaId = FileUtils.upload(WebhookConfig.upload_media_url, "D:/KuGou/汪苏泷 - 有点甜.mp3");
        FileMessage message = new FileMessage(mediaId);
        SendResult result = WechatRobotApi.send(WebhookConfig.send_url, message);
        System.out.println(result);
    }

    /**
     * 视频文件消息
     */
    @Test
    public void testVideo() {
        String mediaId = FileUtils.upload(WebhookConfig.upload_media_url, "D:/Video/明月千古情.mp4");
        FileMessage message = new FileMessage(mediaId);
        SendResult result = WechatRobotApi.send(WebhookConfig.send_url, message);
        System.out.println(result);
    }

    /**
     * 图片文件消息
     */
    @Test
    public void testPicture() {
        String mediaId = FileUtils.upload(WebhookConfig.upload_media_url, "D:/Blog/avatar.png");
        FileMessage message = new FileMessage(mediaId);
        SendResult result = WechatRobotApi.send(WebhookConfig.send_url, message);
        System.out.println(result);
    }
}
