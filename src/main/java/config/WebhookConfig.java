package config;

/**
 * @ClassName WebhookConfig
 * @Description 测试Webhook配置类
 * @Author GengRui
 * @Date 2021/6/7 16:11
 */
public class WebhookConfig {
    private static final String url = "https://qyapi.weixin.qq.com/cgi-bin/webhook/";
    private static final String send = "send?key=";
    private static final String upload_media = "upload_media?type=file&key=";
    //TODO 改成自己用于测试的群机器人webhook地址中key的值
    private static final String key = "693a91f6-7xxx-4bc4-97a0-0ec2sifa5aaa";

    // 消息发送URL
    public static final String send_url = url + send + key;
    // 文件上传URL（文件类型消息上传文件时需要用到的URL）
    public static final String upload_media_url = url + upload_media + key;
}
