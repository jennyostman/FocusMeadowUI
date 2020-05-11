package exarb.fmui.service;

import exarb.fmui.enums.FlowerType;
import exarb.fmui.model.FlowerWeb;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class FlowerService {

    public FlowerService(){}


    /**
     * Takes a list of flowerTypes and converts it to a list of FlowerWeb objects
     * @param flowerTypeList a list containing FlowerType objects
     * @return List<FlowerWeb> a list containing FlowerWeb objects
     */
    public List<FlowerWeb> getMeadowFlowers(List<FlowerType> flowerTypeList){
        HashMap<FlowerType, FlowerWeb> availableFlowerList = new HashMap<>();

        availableFlowerList.put(FlowerType.SUNFLOWER, new FlowerWeb("images/sunflower.jpg", "Sunflower", FlowerType.SUNFLOWER));
        availableFlowerList.put(FlowerType.PANSY, new FlowerWeb("images/pansy.jpg", "Pansy", FlowerType.PANSY));
        availableFlowerList.put(FlowerType.GRASS, new FlowerWeb("images/grass.jpg", "Grass", FlowerType.GRASS));
        availableFlowerList.put(FlowerType.PILEOFDIRT, new FlowerWeb("images/pileofdirt.jpg", "Pile of dirt", FlowerType.PILEOFDIRT));

        List<FlowerWeb> flowerList = new ArrayList<>();

        for (FlowerType flower : flowerTypeList) {
            flowerList.add(availableFlowerList.get(flower));
        }

        return flowerList;
    }
}
