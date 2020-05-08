package exarb.fmui.service;

import exarb.fmui.client.FlowerClient;
import exarb.fmui.model.FlowerType;
import exarb.fmui.model.FlowerWeb;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class FlowerService {

    private FlowerClient flowerClient;

    public FlowerService(FlowerClient flowerClient) {
        this.flowerClient = flowerClient;
    }

    //TODO get from gamelogic
    /**
     * Method to retrieve and structure a list of the flowers for the meadow
     * @return
     */
    public List<FlowerWeb> getMeadowFlowers(){
        FlowerWeb sunflower = new FlowerWeb("images/sunflower.jpg", "Sunflower", FlowerType.SUNFLOWER);
        FlowerWeb pansy = new FlowerWeb("images/pansy.jpg", "Pansy", FlowerType.PANSY);
        FlowerWeb grass = new FlowerWeb("images/grass.jpg", "Grass", FlowerType.GRASS);

        Random random = new Random();
        List<FlowerWeb> flowerList = new ArrayList<>();
        for (int i = 0; i < 64; i++) {
            int ran = random.nextInt(4);
            if ( ran == 0){
                flowerList.add(sunflower);
            } else if (ran == 1){
                flowerList.add(pansy);
            } else {
                flowerList.add(grass);
            }
        }

        return flowerList;
    }
}
