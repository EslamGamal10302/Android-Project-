package com.example.project.area;

public class Area {
    private  int image_thumbnails;
    private String nationality;

    public int getImage_thumbnails() {
        return image_thumbnails;
    }

    public void setImage_thumbnails(int image_thumbnails) {
        this.image_thumbnails = image_thumbnails;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Area(int image_thumbnails, String nationality) {
        this.image_thumbnails = image_thumbnails;
        this.nationality = nationality;
    }
}
