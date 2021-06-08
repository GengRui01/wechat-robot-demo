package utils;

/**
 * @EnumName MarkdownColorEnum
 * @Description markdown字体颜色枚举类
 * @Author GengRui
 * @Date 2021/6/7 19:23
 */
public enum MarkdownColorEnum {
    orange("warning"),
    gray("comment"),
    green("info");

    String value;

    MarkdownColorEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
