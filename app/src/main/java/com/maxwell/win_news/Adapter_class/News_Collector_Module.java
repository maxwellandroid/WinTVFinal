package com.maxwell.win_news.Adapter_class;

/**
 * Created by MAXWELL on 27-04-2017.
 */

public class News_Collector_Module {
    String news_title;
    String news_image;
    String news_full_content;
    String news_time;
    String news_id;


    public String getNews_title() {
        return news_title;
    }

    public void setNews_title(String title) {
        this.news_title = title;
    }

    public String getNews_image() {
        return news_image;
    }

    public void setNews_image(String image) {this.news_image = image;}

    public String getNews_full_content() {
        return news_full_content;
    }

    public void setNews_full_content(String desc) {
        this.news_full_content = desc;
    }

    public void setNews_time(String time) {
        this.news_time = time;
    }

    public String getNews_time() {
        return news_time;
    }

    public void setNews_id(String id) {
        this.news_id = id;
    }

    public String getNews_id() {
        return news_id;
    }


}
