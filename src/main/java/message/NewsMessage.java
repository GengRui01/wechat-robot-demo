package message;

import com.alibaba.fastjson.JSON;
import common.MsgtypeEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName NewsMessage
 * @Description
 * @Author GengRui
 * @Date 2021/6/8 9:25
 */
public class NewsMessage implements Message {
    /**
     * 文章列表 一个图文消息支持1-8篇文章
     */
    private List<Article> articles = new ArrayList<Article>();

    public void addArticle(Article article) {
        if (articles.size() > 8) {
            throw new IllegalArgumentException("每条图文消息所包含的文章数不可超过八个");
        }
        articles.add(article);
    }

    /**
     * 返回消息的Json格式字符串
     *
     * @return 消息的Json格式字符串
     */
    @Override
    public String toJson() throws IllegalArgumentException {
        Map<String, Object> items = new HashMap<String, Object>();
        items.put("msgtype", MsgtypeEnum.news.getValue());

        Map<String, Object> news = new HashMap<String, Object>();
        if (articles.isEmpty()) {
            throw new IllegalArgumentException("图文消息中不可以没有文章");
        }
        news.put("articles", articles);
        items.put("news", news);
        return JSON.toJSONString(items);
    }
}
