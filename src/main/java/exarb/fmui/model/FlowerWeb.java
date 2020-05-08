package exarb.fmui.model;

public class FlowerWeb {
    private String image;
    private String name;
    private FlowerType flowerType;

    public FlowerWeb(String image, String name, FlowerType flowerType) {
        this.image = image;
        this.name = name;
        this.flowerType = flowerType;
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
}
