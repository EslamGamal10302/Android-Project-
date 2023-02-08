package com.example.project.category;

public class Category {
    private String strCategory;
    private  String image;

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category(String strCategory, String image) {
        this.strCategory = strCategory;
        this.image = image;
    }
}
