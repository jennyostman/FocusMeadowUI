package exarb.fmui.model;

import exarb.fmui.enums.FlowerType;

/**
 * Model for the flowers containing the information the frontend needs
 */
public class FlowerWeb {
    private String image;
    private String name;
    private FlowerType flowerType;
    private String price;

    public FlowerWeb(String image, String name, FlowerType flowerType, String price) {
        this.image = image;
        this.name = name;
        this.flowerType = flowerType;
        this.price = price;
    }

    public FlowerWeb() {
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public FlowerType getFlowerType() {
        return flowerType;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "FlowerWeb{" +
                "image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", flowerType=" + flowerType +
                ", price='" + price + '\'' +
                '}';
    }
}
