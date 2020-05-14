package exarb.fmui.model;

public class AchievementWeb {

    private String image;
    private String name;
    private String achievementType;
    private String description;


    public AchievementWeb(String image, String name, String achievementType, String description) {
        this.image = image;
        this.name = name;
        this.achievementType = achievementType;
        this.description = description;
    }

    public AchievementWeb() {
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getAchievementType() {
        return achievementType;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "AchievementWeb{" +
                "image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", achievementType='" + achievementType + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
