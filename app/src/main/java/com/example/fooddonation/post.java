package com.example.fooddonation;

public class post {
    String image,caption;

    public post() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public post(String image, String caption) {
        this.image = image;
        this.caption = caption;
    }
}
