package message;

/**
 * @ClassName Article
 * @Description 文章类（一个图文消息支持1-8篇文章）
 * @Author GengRui
 * @Date 2021/6/8 9:00
 */
public class Article {
    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     * 可省略
     */
    private String description;
    /**
     * 点击后跳转的链接
     */
    private String url;
    /**
     * 图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图 1068*455，小图150*150
     * 可省略
     */
    private String picurl;

    public Article(String title, String description, String url, String picurl) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.picurl = picurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }
}
