package utils;

import java.util.List;

/**
 * @ClassName MarkdownUtils
 * @Description markdown工具类
 * @Author GengRui
 * @Date 2021/6/7 19:27
 */
public class MarkdownUtils {
    /**
     * 标题
     *
     * @param headerType 标题等级
     * @param text       标题内容
     * @return
     */
    public static String getHeaderText(int headerType, String text) throws IllegalArgumentException {
        if (headerType < 1 || headerType > 6) {
            throw new IllegalArgumentException("标题等级必须在1-6之间");
        }
        StringBuffer numbers = new StringBuffer();
        for (int i = 0; i < headerType; i++) {
            numbers.append("#");
        }
        return numbers + " " + text;
    }

    /**
     * 加粗
     *
     * @param text
     * @return
     */
    public static String getBoldText(String text) {
        return "**" + text + "**";
    }

    /**
     * 链接
     *
     * @param text 连接标题
     * @param href 具体链接
     * @return
     */
    public static String getLinkText(String text, String href) {
        return "[" + text + "](" + href + ")";
    }

    /**
     * 行内代码段（暂不支持跨行）
     *
     * @param text
     * @return
     */
    public static String getCodeText(String text) {
        return "`" + text + "`";
    }

    /**
     * 引用
     *
     * @param text
     * @return
     */
    public static String getQuoteText(String text) {
        return ">" + text;
    }

    /**
     * 字体颜色（只支持3种内置颜色）
     *
     * @param markdownColorEnum
     * @param text
     * @return
     */
    public static String getColorText(MarkdownColorEnum markdownColorEnum, String text) {
        return "<font color=\"" + markdownColorEnum.value + "\">" + text + "</font>";
    }

    /**
     * 斜体
     *
     * @param text
     * @return
     */
    public static String getItalicText(String text) {
        return "*" + text + "*";
    }

    /**
     * 有序列表
     *
     * @param orderList
     * @return
     */
    public static String getOrderListText(List<String> orderList) {
        if (orderList.isEmpty()) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= orderList.size() - 1; i++) {
            sb.append(String.valueOf(i) + ". " + orderList.get(i - 1) + "\n");
        }
        sb.append(String.valueOf(orderList.size()) + ". " + orderList.get(orderList.size() - 1));
        return sb.toString();
    }

    /**
     * 无序列表
     *
     * @param unOrderList
     * @return
     */
    public static String getUnOrderListText(List<String> unOrderList) {
        if (unOrderList.isEmpty()) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < unOrderList.size() - 1; i++) {
            sb.append("- " + unOrderList.get(i) + "\n");
        }
        sb.append("- " + unOrderList.get(unOrderList.size() - 1));
        return sb.toString();
    }
}
