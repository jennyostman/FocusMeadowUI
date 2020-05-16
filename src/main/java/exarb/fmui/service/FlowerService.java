package exarb.fmui.service;

import exarb.fmui.client.FlowerClient;
import exarb.fmui.client.dto.UserGameData;
import exarb.fmui.enums.FlowerType;
import exarb.fmui.model.FlowerWeb;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Service fpr handling flowers
 */
@Service
public class FlowerService {

    private final FlowerClient flowerClient;
    private Map<FlowerType, FlowerWeb> allFlowersMap;

    public FlowerService(FlowerClient flowerClient){
        this.flowerClient = flowerClient;
        allFlowersMap = flowerClient.getAllFlowersMap();
    }


    /**
     * Takes a list of flowerTypes and converts it to a list of FlowerWeb objects
     * @param flowerTypeList a list containing FlowerType objects
     * @return List<FlowerWeb> a list containing FlowerWeb objects
     */
    public List<FlowerWeb> getMeadowFlowers(List<FlowerType> flowerTypeList){
        List<FlowerWeb> flowerList = new ArrayList<>();

        for (FlowerType flower : flowerTypeList) {
            flowerList.add(allFlowersMap.get(flower));
        }

        return flowerList;
    }

    /**
     * Creates a List containing all flowers (not including GRASS and PILEOFDIRT) that is not on the List given
     * @param flowerTypeList
     * @return List<FlowerWeb>
     */
    public List<FlowerWeb> getShopFlowers(List<FlowerType> flowerTypeList){
        List<FlowerWeb> flowerList = new ArrayList<>();

        if (allFlowersMap != null){
            allFlowersMap.forEach((k, v) -> {
                if (!flowerTypeList.contains(k) && k != FlowerType.GRASS && k != FlowerType.PILEOFDIRT){
                    flowerList.add(v);
                }
            });
        }

        return flowerList;
    }

    /**
     * Adds the chosen flower to the users choosableFlowers List in the
     * database and returns the updated UserGameData object
     * @param flowerType
     * @param userId
     * @return UserGameData
     */
    public UserGameData buyFlower(FlowerType flowerType, String userId){
        return(flowerClient.buyFlower(flowerType, userId));
    }
}
