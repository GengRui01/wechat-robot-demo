package common;

/**
 * @EnumName MsgtypeEnum
 * @Description 消息类型枚举类
 * @Author GengRui
 * @Date 2021/6/7 13:51
 */
public enum MsgtypeEnum {
    /**
     * 文本类型
     */
    text("text"),
    /**
     * markdown类型
     */
    markdown("markdown"),
    /**
     * 图片类型
     */
    image("image"),
    /**
     * 图文类型
     */
    news("news"),
    /**
     * 文件类型
     */
    file("file");

    String value;

    MsgtypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
