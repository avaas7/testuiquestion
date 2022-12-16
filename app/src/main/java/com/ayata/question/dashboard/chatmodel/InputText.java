package com.ayata.question.dashboard.chatmodel;

public class InputText {
    private String token;
    private String text;

    public InputText(String token, String text) {
        this.token = token;
        this.text = text;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
