package com.example.squadroncinema;


public class Food {
    private String Name;
    private String Description;
    private String price;

    public Food() {}

    public Food(String name, String description, String price) {
        Name = name;
        Description = description;
        this.price = price;
    }

    public Food(String name, String price) {
        Name = name;
        this.price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
