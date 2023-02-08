package com.example.project.Ingredient;

public class meals {
   private String strIngredient;

    public String getStrIngredient() {
        return strIngredient;
    }

    public void setStrIngredient(String strIngredient) {
        this.strIngredient = strIngredient;
    }

    public meals(String strIngredient, String image) {
        this.strIngredient = strIngredient;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private  String image;


}
