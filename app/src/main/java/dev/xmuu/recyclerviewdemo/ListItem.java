package dev.xmuu.recyclerviewdemo;

public class ListItem {

    int type;       // Item 的类型
    String title;   // Item 的标题
    String brief;   // Item 的简介
    String image;   // Item 的图片

    /**
     * 构造函数
     **/
    public ListItem(int type, String title, String brief, String image) {
        this.type = type;
        this.title = title;
        this.brief = brief;
        this.image = image;
    }

}
