package utils;

import message.ImageMessage;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

/**
 * @ClassName ImageUtils
 * @Description 图片工具类
 * @Author GengRui
 * @Date 2021/6/8 8:43
 */
public class ImageUtils {
    /**
     * 本地图片转换
     *
     * @param imgFile 图片本地路径
     * @return
     * @author yanceyzhang
     */
    public static ImageMessage toMessageByLocal(String imgFile) {
        byte[] data = null;
        try {
            InputStream in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
            String base64 = Base64.getEncoder().encodeToString(data);
            return new ImageMessage(base64, DigestUtils.md5Hex(data));
        } catch (IOException e) {
            throw new IllegalArgumentException("本地图片读取IO异常");
        }
    }

    /**
     * 线上图片转换
     *
     * @param imgURL 图片线上路径
     * @return
     * @author yanceyzhang
     */
    public static ImageMessage toMessageByOnline(String imgURL) {
        try {
            ByteArrayOutputStream data = new ByteArrayOutputStream();
            // 创建URL
            URL url = new URL(imgURL);
            byte[] by = new byte[1024];
            // 创建链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(20000);
            conn.setReadTimeout(20000);
            InputStream is = conn.getInputStream();
            // 将内容读取内存中
            int len = -1;
            while ((len = is.read(by)) != -1) {
                data.write(by, 0, len);
            }
            byte[] b = data.toByteArray();
            String base64 = Base64.getEncoder().encodeToString(b);
            String md5 = DigestUtils.md5Hex(b);
            is.close();
            conn.disconnect();
            data.close();
            return new ImageMessage(base64, md5);
        } catch (IOException e) {
            throw new IllegalArgumentException("线上图片读取IO异常");
        }
    }
}
