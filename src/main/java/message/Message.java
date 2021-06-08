package message;

/**
 * @InterfaceName Message
 * @Description 消息通用接口
 * @Author GengRui
 * @Date 2021/6/7 14:38
 */
public interface Message {
    /**
     * 返回消息的Json格式字符串
     *
     * @return 消息的Json格式字符串
     */
    String toJson() throws IllegalArgumentException;
}
