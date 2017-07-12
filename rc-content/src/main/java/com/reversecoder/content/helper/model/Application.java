package com.reversecoder.content.helper.model;

public class Application extends WrapperBase {

    private final String title;

    public Application() {
        this.title = "";
    }

    public Application(String _title) {
        this.title = _title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Application{" +
                "title='" + title + '\'' +
                '}';
    }
}