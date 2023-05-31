package com.example.myday.DB;

public class DBStruct {
    private Integer id;
    private String title;
    private String date;
    private String content;

    //add by v2
    private String weather;

    //struct v2
    public DBStruct(Integer id, String title, String date, String content, String weather) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.content = content;
        this.weather = weather;
    }

//    public DBStruct(int id, String title, String date, String content) {
//        this.id = id;
//        this.title = title;
//        this.date = date;
//        this.content = content;
//    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    //    public DBStruct(Integer id, String title, String date, String content) {
//        this.id = id;
//        this.title = title;
//        this.date = date;
//        this.content = content;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public String getContent() {
//        return content;
//    }
}
