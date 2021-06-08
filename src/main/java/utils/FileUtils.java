package utils;

import com.alibaba.fastjson.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

/**
 * @ClassName FileUtils
 * @Description 文件工具类
 * @Author GengRui
 * @Date 2021/6/8 11:35
 */
public class FileUtils {
    /**
     * 文件上传得到media_id，该media_id仅三天内有效
     * media_id在同一企业内应用之间可以共享
     *
     * @param uploadMediaUrl
     * @param filePath
     * @return
     */
    public static String upload(String uploadMediaUrl, String filePath) {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new IllegalArgumentException("文件不存在");
        }
        // 返回结果
        String result;
        try {
            // 设置边界
            String boundary = "----------" + System.currentTimeMillis();
            // 获取连接并设置请求头
            HttpsURLConnection conn = getConnection(uploadMediaUrl, boundary);
            // 设置请求正文信息
            setFileContent(conn.getOutputStream(), file, boundary);

            if (HttpsURLConnection.HTTP_OK != conn.getResponseCode()) {
                throw new IllegalArgumentException("Http请求错误 错误编码为" + conn.getResponseCode());
            } else {
                // 获取请求结果
                result = getResult(conn.getInputStream());
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("HTTP请求IO异常");
        }
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (!jsonObject.getInteger("errcode").equals(0)) {
            throw new IllegalArgumentException(jsonObject.getString("errmsg"));
        }
        return jsonObject.getString("media_id");
    }

    /**
     * 获取连接并设置请求头
     *
     * POST https://qyapi.weixin.qq.com/cgi-bin/webhook/upload_media?key=693a91f6-7xxx-4bc4-97a0-0ec2sifa5aaa&type=file HTTP/1.1
     * Content-Type: multipart/form-data; boundary=-------------------------acebdf13572468
     * Content-Length: 220
     *
     * @param uploadMediaUrl 地址
     * @param boundary       边界值
     * @return
     * @throws IOException
     */
    private static HttpsURLConnection getConnection(String uploadMediaUrl, String boundary) throws IOException {
        URL url = new URL(uploadMediaUrl);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("POST");// 以POST方式提交表单
        conn.setUseCaches(false);// POST方式不能使用缓存
        conn.setDoInput(true);
        conn.setDoOutput(true);
        // 设置请求头信息
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Charset", "UTF-8");
        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        conn.setRequestProperty("Content-Length", "220");
        return conn;
    }

    /**
     * 设置请求正文信息
     *
     * ---------------------------acebdf13572468
     * Content-Disposition: form-data; name="media";filename="wework.txt"; filelength=6
     * Content-Type: application/octet-stream
     *
     * mytext
     * ---------------------------acebdf13572468--
     *
     * @param outputStream
     * @param file
     * @param boundary
     * @throws IOException
     */
    private static void setFileContent(OutputStream outputStream, File file, String boundary) throws IOException {
        // 头部内容
        StringBuilder sb = new StringBuilder();
        sb.append("--");// 必须多两条道
        sb.append(boundary);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data;name=\"media\"; filename=\"" + file.getName() + "\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");
        outputStream.write(sb.toString().getBytes("UTF-8"));
        // 正文部分 把文件以流的方式读取后推送道URL中
        DataInputStream din = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] buffer = new byte[1024];
        while ((bytes = din.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytes);
        }
        din.close();
        // 尾部内容
        byte[] foot = ("\r\n--" + boundary + "--\r\n").getBytes("UTF-8");// 定义数据最后分割线
        outputStream.write(foot);
        outputStream.flush();
        outputStream.close();
    }

    /**
     * 获取请求结果
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    private static String getResult(InputStream inputStream) throws IOException {
        StringBuffer strbuffer = new StringBuffer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String lineString;
        while ((lineString = reader.readLine()) != null)
            strbuffer.append(lineString);
        reader.close();
        return strbuffer.toString();
    }
}
